package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.sync.AsyncTaskEndPoint;
import com.udacity.gradle.display.JokeDisplayActivity;
import com.udacity.radsen.jokes.backend.myApi.MyApi;

import java.io.IOException;


public class MainActivity extends AppCompatActivity implements AsyncTaskEndPoint.EndPointListener {

    private LinearLayout progress;

    private InterstitialAd mInterstitialAd;
    private Context context;
    private AsyncTaskEndPoint.EndPointListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress = (LinearLayout) findViewById(R.id.progress);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.banner_ad_unit_id));

        context = this;
        listener = this;

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                new AsyncTaskEndPoint(context, listener).execute();
            }
        });

        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    public void tellJoke(View view) {
        if(mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        } else {
            new AsyncTaskEndPoint(context, listener).execute();
        }
    }

    @Override
    public void onBeforeStart() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccess(String joke) {
        progress.setVisibility(View.GONE);
        Intent iJoke = new Intent(this, JokeDisplayActivity.class);
        iJoke.putExtra(JokeDisplayActivity.JOKE_KEY, joke);
        startActivity(iJoke);
    }

    @Override
    public void onError() {
        progress.setVisibility(View.GONE);
        Toast.makeText(this, getString(R.string.error_loading_joke),
                Toast.LENGTH_SHORT).show();
    }
}
