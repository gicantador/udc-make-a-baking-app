package com.pgcn.udcmakeabaking;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Giselle on 21/03/2018.
 */
@RunWith(AndroidJUnit4.class)
public class TestMainAcitivty {


    @Rule
    public ActivityTestRule<BakingTimeActivity> activityActivityTestRule = new ActivityTestRule<BakingTimeActivity>(BakingTimeActivity.class);

    @Before
    public void init() {
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction();
    }

    @Test
    public void TestCarregamento() {

        onView(withId(R.id.tv_baking_title)).check(matches((isDisplayed())));
    }
}
