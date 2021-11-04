package com.app.mob.halloween;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.ads.consent.ConsentForm;
import com.google.ads.consent.ConsentFormListener;
import com.google.ads.consent.ConsentInfoUpdateListener;
import com.google.ads.consent.ConsentInformation;
import com.google.ads.consent.ConsentStatus;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Button btn_rate;
    ImageButton bt_menu;
    TextView txt_title;

    private RecyclerView recyclerView;
    private HallowStoryAdapter hallowStoryAdapter;
    private List<Hallow> hallowList;

    ProgressBar progressBar;
    ConsentForm form;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ConsentInformation consentInformation = ConsentInformation.getInstance(this);
        String[] publisherIds = {"pub-7734946040183698"};
        consentInformation.requestConsentInfoUpdate(publisherIds, new ConsentInfoUpdateListener() {
            @Override
            public void onConsentInfoUpdated(ConsentStatus consentStatus) {
                // User's consent status successfully updated.
            }
            @Override
            public void onFailedToUpdateConsentInfo(String errorDescription) {
                // User's consent status failed to update.
            }
        });
        URL privacyUrl = null;
        try {
            // TODO: Replace with your app's privacy policy URL.
            privacyUrl = new URL("https://sites.google.com/view/appmobe/privacy-policy");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            // Handle error.
        }
        form = new ConsentForm.Builder(this, privacyUrl)
                .withListener(new ConsentFormListener() {
                    @Override
                    public void onConsentFormLoaded() {
                        // Consent form loaded successfully.
                        form.show();
                    }
                    @Override
                    public void onConsentFormOpened() {
                        // Consent form was displayed.
                    }
                    @Override
                    public void onConsentFormClosed(
                            ConsentStatus consentStatus, Boolean userPrefersAdFree) {
                        // Consent form was closed.
                    }
                    @Override
                    public void onConsentFormError(String errorDescription) {
                        // Consent form error.
                    }
                })
                .withPersonalizedAdsOption()
                .withNonPersonalizedAdsOption()
                .withAdFreeOption()
                .build();
        form.load();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        btn_rate = findViewById(R.id.btn_rate);
        bt_menu = findViewById(R.id.btn_menu);
        txt_title = findViewById(R.id.txt_title);
        recyclerView= findViewById(R.id.recyclerView);
        progressBar= findViewById(R.id.progressBar);



        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        hallowList =  new ArrayList<>();
        hallowStoryAdapter = new HallowStoryAdapter(this, hallowList);
        recyclerView.setAdapter(hallowStoryAdapter);

        readHallowStory();




        btn_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 rateApp();
            }
        });
        bt_menu.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.openDrawer(Gravity.START);
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        toggle.setDrawerIndicatorEnabled(false);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);

        Menu menu = navigationView.getMenu();
        MenuItem tools2= menu.findItem(R.id.tools2);

        SpannableString s2 = new SpannableString(tools2.getTitle());
        s2.setSpan(new TextAppearanceSpan(this, R.style.TextAppearance44), 0, s2.length(), 0);
        tools2.setTitle(s2);

        navigationView.setNavigationItemSelectedListener(this);


        Paper.init(this);

        FirebaseMessaging.getInstance().subscribeToTopic(Common.topicHalloween);
        //write value
        Paper.book().write("sub_new","true");





    }




    private void readHallowStory() {

        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference(getString(R.string.app_Halloween))
                .child("Halloween").child("Stories");
        //Query query = reference.orderByChild("isComplete").equalTo("false");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                hallowList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Hallow hallow= snapshot.getValue(Hallow.class);
                    hallowList.add(hallow);
                }
                Collections.reverse(hallowList);
                hallowStoryAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_share) {
            SharePage();
        } else if (id == R.id.nav_more) {
            startActivity(new Intent(MainActivity.this, MoreAppsActivity.class));
            overridePendingTransition(R.anim.enter, R.anim.exit);
        } else if (id == R.id.nav_send) {
            startActivity(new Intent(MainActivity.this, ContactActivity.class));
            overridePendingTransition(R.anim.enter, R.anim.exit);
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
            overridePendingTransition(R.anim.enter, R.anim.exit);
        } else if (id == R.id.nav_privacy_policy) {
            startActivity(new Intent(MainActivity.this, PolicyActivity.class));
            overridePendingTransition(R.anim.enter, R.anim.exit);
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void SharePage() {
        String shareBody = "The best application contains Halloween Stories:";
        String shareSub = "https://play.google.com/store/apps/details?id=com.app.mob.halloween";
        Intent myIntent= new Intent(Intent.ACTION_SEND);
        myIntent.setType("text/plain");
        myIntent.putExtra(Intent.EXTRA_TEXT,shareBody+"\n"+ shareSub);
        startActivity(myIntent);
    }
    private void rateApp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                MainActivity.this);
        builder.setIcon(R.drawable.ic_rate);
        builder.setMessage(getResources().getString(R.string.ratethisapp_msg));
        builder.setTitle(getResources().getString(R.string.ratethisapp_title));
        builder.setPositiveButton(
                getResources().getString(R.string.rate_it),
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        // TODO Auto-generated method stub
                        Intent fire = new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://play.google.com/store/apps/details?id=com.app.mob.halloween"));
                        //dz.amine.thequotesgarden"));
                        startActivity(fire);
                    }
                });

        builder.setNegativeButton(
                getResources().getString(R.string.cancel),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                    }
                });
        builder.create();
        builder .show();
    }

}