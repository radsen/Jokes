package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress = (LinearLayout) findViewById(R.id.progress);
    }

    public void tellJoke(View view) {
        new AsyncTaskEndPoint(this, this).execute();
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
        Toast.makeText(this, getString(R.string.error_loading_joke), Toast.LENGTH_SHORT).show();
    }
}
