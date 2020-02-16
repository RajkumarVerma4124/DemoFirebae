package com.example.addapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class Main2Activity extends AppCompatActivity {
    private InterstitialAd interstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


    }

    @Override
    public void onBackPressed() {
        AdRequest adRequest = new AdRequest.Builder().build();
        //Prepare the interstitial ad
        interstitial = new InterstitialAd(Main2Activity.this);
        //Insert the ad unit Id
        interstitial.setAdUnitId(getString(R.string.admob_interstitial_id));
        interstitial.loadAd(adRequest);
        //Prepare an Interstitial Ad Listener
        interstitial.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                //call display Interstitial () function
                displayInterstitial();
            }

        });
        Intent i = new Intent(Main2Activity.this,MainActivity.class);
        startActivity(i);
        finish();

    }

    private void displayInterstitial() {

        //if ads are loaded show interstitial else show nothing
        if(interstitial.isLoaded());
        {
            interstitial.show();
        }
    }

}

