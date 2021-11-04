package com.app.mob.halloween;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.app.mob.halloween.databinding.ActivityMoreAppsBinding;
import com.app.mob.halloween.databinding.ActivityStoryDetailBinding;
import com.squareup.picasso.Picasso;

public class MoreAppsActivity extends AppCompatActivity {

    ActivityMoreAppsBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMoreAppsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        Picasso.with(MoreAppsActivity.this).load("https://lh3.googleusercontent.com/qjg3SZllDqn7eCp9DRkEKFykx3TTeWo23mSgZ1sxUlTT93rQvk-5nkcGQGkqEYCMhg=s180").into(binding.philosophyQu);
        Picasso.with(MoreAppsActivity.this).load("https://lh3.googleusercontent.com/7M8mu3itNWIHDS5BIPLCKNe8NoQD_94eZicha3a-LYA9vu4d56rQ5-HhgHQooe7B7A=s180").into(binding.animeQuEng);
        Picasso.with(MoreAppsActivity.this).load("https://lh3.googleusercontent.com/T9atzKzfx6ineO4LEvVExqYDX7Ia6tSzwrjM3Oz0rCVlkSMPq5DmRkBiRyX6rlUDr80=s180").into(binding.animeQuAr);
        Picasso.with(MoreAppsActivity.this).load("https://lh3.googleusercontent.com/uhjBiX29vgjM_YhW370CjKgILtOiBm24lCQaa0TDOtt1Np4ypPdwVDG4yHJgXtSN4g=s180").into(binding.PsyFacts);
        Picasso.with(MoreAppsActivity.this).load("https://play-lh.googleusercontent.com/zQmsZYHjxEeTrc3PgKT8QHcAk1KjReajKSIkakKdGCEwhXcP6ZUK7pbqBZ23ao5DvQ=s180").into(binding.scientistsQuotes);

        binding.PsyFacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData("com.app.mob.psyfacts");
            }
        });

        binding.philosophyQu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData("com.app.mobe.philosophyquotes");
            }
        });

        binding.animeQuAr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData("com.app.mob.animequotes");
            }
        });
        binding.animeQuEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData("com.app.mob.animequoteseng");
            }
        });
        binding.scientistsQuotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData("com.app.mob.muslimscholars");
            }
        });

    }

    private void setData(String s) {
        final String appPackageName = s; // getPackageName() from Context or Activity object
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }

    }

}