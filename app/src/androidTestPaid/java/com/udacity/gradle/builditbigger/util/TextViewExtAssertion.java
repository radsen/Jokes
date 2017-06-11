package com.udacity.gradle.builditbigger.util;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Matcher;

import static org.junit.Assert.assertFalse;

/**
 * Created by radsen on 6/10/17.
 */

public class TextViewExtAssertion implements ViewAssertion {

    public static TextViewExtAssertion isNotEmpty(){
        return new TextViewExtAssertion();
    }

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if(noViewFoundException != null){
            throw noViewFoundException;
        }

        TextView textView = (TextView) view;
        assertFalse(TextUtils.isEmpty(textView.getText().toString()));
    }
}
