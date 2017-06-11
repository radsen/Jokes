package com.udacity.gradle.display;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by radsen on 6/9/17.
 */

public class JokeDisplayFragment extends Fragment {

    public JokeDisplayFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_joke_display, container, false);
        Intent intent = getActivity().getIntent();
        String joke = intent.getStringExtra(JokeDisplayActivity.JOKE_KEY);
        TextView jokeTextView = (TextView) root.findViewById(R.id.tv_joke);
        if (joke != null && joke.length() != 0) {
            jokeTextView.setText(joke);
        }

        return root;

    }
}
