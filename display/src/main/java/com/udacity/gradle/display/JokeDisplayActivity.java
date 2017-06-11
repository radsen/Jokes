package com.udacity.gradle.display;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by radsen on 6/9/17.
 */

public class JokeDisplayActivity extends AppCompatActivity {

    public static final String JOKE_KEY = JokeDisplayActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);
    }
}
