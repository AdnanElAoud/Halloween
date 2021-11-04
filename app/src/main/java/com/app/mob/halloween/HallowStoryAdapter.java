package com.app.mob.halloween;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.app.mob.halloween.Ads.NativeTemplateStyle;
import com.app.mob.halloween.Ads.TemplateView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HallowStoryAdapter extends RecyclerView.Adapter<HallowStoryAdapter.ViewHolder>{

    private Context mContext;
    private List<Hallow> mHallows;


    public HallowStoryAdapter(Context mContext, List<Hallow> mHallows) {
        this.mContext = mContext;
        this.mHallows = mHallows;
        //this.viewTypes = viewTypes;
    }

    @NonNull
    @Override
    public HallowStoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(mContext).inflate(R.layout.item_story, parent , false);
        return new HallowStoryAdapter.ViewHolder(view);

    }



    @Override
    public void onBindViewHolder(@NonNull final HallowStoryAdapter.ViewHolder viewHolder, int position) {



        final Hallow hallow = (Hallow) mHallows.get(position);


        if ( position % 25 == 7 ) {
            viewHolder.relativeLayoutAds.setVisibility(View.VISIBLE);
            MobileAds.initialize(mContext, mContext.getResources().getString(R.string.app_id));
            AdLoader adLoader = new AdLoader.Builder(mContext,mContext.getResources().getString(R.string.admob_native1))
                    .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                        @Override
                        public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                            //   NativeTemplateStyle styles = new
                            //          NativeTemplateStyle.Builder().withMainBackgroundColor(g).build();

                            //template.setStyles(styles);
                           /* viewHolder.template.setNativeAd(unifiedNativeAd);
                            viewHolder.relativeLayoutAds.setVisibility(View.VISIBLE);

*/
                            NativeTemplateStyle styles = new
                                    NativeTemplateStyle.Builder().build();
                            viewHolder.template.setVisibility(View.VISIBLE);
                            viewHolder.template.setStyles(styles);
                            viewHolder.template.setNativeAd(unifiedNativeAd);
                        }
                    }).withAdListener(new AdListener() {
                        @Override
                        public void onAdFailedToLoad(int errorCode) {
                            viewHolder.template.setVisibility(View.GONE);
                            // Handle the failure by logging, altering the UI, and so on.
                        }
                    }).withNativeAdOptions(new NativeAdOptions.Builder()
                            // Methods in the NativeAdOptions.Builder class can be
                            // used here to specify individual options settings.
                            .build())
                    .build();

            adLoader.loadAd(new AdRequest.Builder().build());
        }else {
            viewHolder.relativeLayoutAds.setVisibility(View.GONE);

        }


        Picasso.with(mContext).load(hallow.getHallowImg()).into(viewHolder.img_fact);
        viewHolder.title_fact.setText(hallow.getHallowName());

        viewHolder.img_fact.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                Intent intent =  new Intent(mContext, StoryDetailActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("HallowId", hallow.getHallowId());
                intent.putExtra("HallowImg", hallow.getHallowImg());
                intent.putExtra("HallowName", hallow.getHallowName());
                intent.putExtra("HallowStory", hallow.getHallowStory());

                mContext.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return   mHallows.size();
    }


    @Override
    public int getItemViewType(int position) {
            return mHallows.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img_fact;
        public TextView title_fact;
        RelativeLayout relativeLayoutAds;
         TemplateView template;
        LinearLayout  main;

        @SuppressLint("NewApi")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_fact = itemView.findViewById(R.id.img_fact);
            title_fact = itemView.findViewById(R.id.title_fact);

            main = itemView.findViewById(R.id.main);
            relativeLayoutAds = itemView.findViewById(R.id.relativeLayoutAds);
            template = itemView.findViewById(R.id.my_template);
            main.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.item_story));

        }
    }
}
