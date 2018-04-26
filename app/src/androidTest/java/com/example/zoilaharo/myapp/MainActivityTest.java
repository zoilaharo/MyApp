package com.example.zoilaharo.myapp;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;


import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.DatePicker;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule
            = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void form() {
        onView(withId(R.id.name_edittext))
                .perform(typeText("Zoila"), closeSoftKeyboard());

        onView(withId(R.id.email_edittext))
                .perform(typeText("zoila@myemail.com"), closeSoftKeyboard());

        onView(withId(R.id.username_edittext))
                .perform(typeText("buttercup"), closeSoftKeyboard());

        setDate(R.id.datePickerDialogButton, 2010, 1, 1);

        onView(withText(R.string.agetoast)).inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));

        setDate(R.id.datePickerDialogButton, 1994, 1, 1);

        onView(withId(R.id.signup_button)).perform(click());



        onView(withId(R.id.name_acct_textview))
                .check(matches(withText("Name")));

        onView(withId(R.id.name_acct))
                .check(matches(withText("Zoila")));

        onView(withId(R.id.username_acct_textview))
                .check(matches(withText("User Name")));

        onView(withId(R.id.username_acct))
                .check(matches(withText("buttercup")));

        onView(withId(R.id.age_acct_textview))
                .check(matches(withText("Age")));

        onView(withId(R.id.age_acct))
                .check(matches(withText("24")));

        onView(withId(R.id.description_acct_edittext))
                .perform(typeText("I like to hike every morning."), closeSoftKeyboard());

        onView(withId(R.id.occupation_acct_edittext))
                .perform(typeText("Professional Skater"), closeSoftKeyboard());


        //TestUtils.rotateScreen(activityTestRule.getActivity());
        TestUtils.rotateScreen(mActivityRule.getActivity());

        onView(withId(R.id.name_acct))
                .check(matches(withText("Zoila")));

        onView(withId(R.id.username_acct_textview))
                .check(matches(withText("User Name")));

        onView(withId(R.id.username_acct))
                .check(matches(withText("buttercup")));

        onView(withId(R.id.age_acct_textview))
                .check(matches(withText("Age")));

        onView(withId(R.id.age_acct))
                .check(matches(withText("24")));

        onView(withId(R.id.description_acct_edittext))
                .check(matches(withText("I like to hike every morning.")));


        onView(withId(R.id.occupation_acct_edittext))
                .check(matches(withText("Professional Skater")));

        onView(withId(R.id.bt_go_back)).perform(click());

    }

    public static void setDate(int datePickerLaunchViewId, int year, int monthOfYear, int dayOfMonth) {
        onView(withId(datePickerLaunchViewId)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, monthOfYear, dayOfMonth));
        onView(withId(android.R.id.button1)).perform(click());
    }

    @Test
    public void canGoToSecondActivityWithMessage() {
        onView(withId(R.id.name_edittext)).perform(typeText("Zoila"));
        onView(withId(R.id.username_edittext)).perform(typeText("buttercup"));
        onView(withId(R.id.age_edittext)).perform(typeText("24"));

        try {
            Intents.init();
            onView(withId(R.id.signup_button)).perform(scrollTo(), click());
            intended(hasComponent(Account.class.getName()));
            intended(hasExtra("name_edittext", "Zoila"));
            intended(hasExtra("username_edittext", "buttercup"));
            intended(hasExtra("age_edittext", "24"));

        } finally {
            Intents.release();
        }
    }

}

