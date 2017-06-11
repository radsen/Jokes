package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.udacity.gradle.builditbigger.util.TextViewExtAssertion.isNotEmpty;

/**
 * Created by radsen on 6/10/17.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void jokeRetrievedAndDisplayedTest(){
        onView(withId(R.id.btn_tell_joke)).perform(click());

        onView(withId(R.id.tv_joke)).check(matches(isDisplayed()));

        onView(withId(R.id.tv_joke)).check(isNotEmpty());
    }

}
