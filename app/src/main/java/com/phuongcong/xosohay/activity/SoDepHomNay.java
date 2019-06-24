package com.phuongcong.xosohay.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.phuongcong.xosohay.R;

import java.text.SimpleDateFormat;

public class SoDepHomNay extends AppCompatActivity {
    TextView textView;
    private AdView adView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_so_dep_hom_nay);
        textView = (TextView) findViewById(R.id.number);
        String android_id = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String aaa = "hfghjghb";
        String day = dateFormat.format(System.currentTimeMillis());
        //    Toast.makeText(SoDepHomNay.this, "Số đẹp của bạn là:  " + stringToNumber(android_id) + day, Toast.LENGTH_LONG).show();
        long sodep = Long.parseLong((stringToNumber(android_id) + day).replace("-", ""));
        sodep = sodep * 7 * 11;
        do {
            if (sodep > 99 && sodep / 2 != 0) {
                sodep = sodep / 2;
            }
            if (sodep > 99 && sodep / 3 != 0) {
                sodep = sodep / 3;
            }
            if (sodep > 99 && sodep / 5 != 0) {
                sodep = sodep / 5;
            }
            if (sodep > 99 && sodep / 7 != 0) {
                sodep = sodep / 7;
            }


        } while (0 > sodep || sodep > 99);
        String sd = "99";
        if (sodep < 10) {
            sd = "0" + sodep;
        } else {
            sd = sodep + "";
        }
        textView.setText(sd);
        setAdmodads();
        //  Toast.makeText(SoDepHomNay.this, "Số đẹp của bạn là:  " + sd, Toast.LENGTH_LONG).show();

    }

    private InterstitialAd mInterstitialAd;

    private void setAdmodads() {
        MobileAds.initialize(this,
                "ca-app-pub-6929505284145846~1936967935");
        adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();

        }
    }

    public static long stringToNumber(String s) {
        long result = 0;

        for (int i = 0; i < s.length(); i++) {
            final char ch = s.charAt(i);
            result += (int) ch;
        }
        return result;
    }
}
