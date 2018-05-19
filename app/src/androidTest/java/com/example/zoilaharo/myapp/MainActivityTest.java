package com.example.zoilaharo.myapp;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import android.content.Intent;
import android.os.SystemClock;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.DatePicker;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void form() {
            onView(withId(R.id.name_edittext))
                    .perform(typeText("Ruby Roundhouse"), closeSoftKeyboard());

            onView(withId(R.id.email_edittext))
                    .perform(typeText("ruby@myemail.com"), closeSoftKeyboard());

            onView(withId(R.id.username_edittext))
                    .perform(typeText("avatar"), closeSoftKeyboard());

            onView(withId(R.id.description_edittext))
                    .perform(typeText("Commando, martial artist, and dance fighter."), closeSoftKeyboard());

            onView(withId(R.id.occupation_edittext))
                    .perform(typeText("Martha's avatar"), closeSoftKeyboard());

        setDate(R.id.datePickerDialogButton, 2010, 1, 1);

        onView(withText(R.string.agetoast)).inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));

        setDate(R.id.datePickerDialogButton, 1994, 1, 1);


        //TestUtils.rotateScreen(activityTestRule.getActivity());
        TestUtils.rotateScreen(mActivityRule.getActivity());


        onView(withId(R.id.name_edittext))
                .check(matches(withText("Ruby Roundhouse")));

        onView(withId(R.id.email_edittext))
                .check(matches(withText("ruby@myemail.com")));

        onView(withId(R.id.username_edittext))
                .check(matches(withText("avatar")));

        onView(withId(R.id.age_edittext))
                .check(matches(withText("24")));

        onView(withId(R.id.description_edittext))
                .check(matches(withText("Commando, martial artist, and dance fighter.")));

        onView(withId(R.id.occupation_edittext))
                .check(matches(withText("Martha's avatar")));

        onView(withId(R.id.signup_button)).perform(scrollTo());
        onView(withId(R.id.signup_button)).perform(click());

        onView(withId(R.id.name_acct_textview))
                .check(matches(withText("Name")));

        onView(withId(R.id.name_acct_profile))
                .check(matches(withText("Ruby Roundhouse")));

        onView(withId(R.id.username_acct_textview))
                .check(matches(withText("User Name")));

        onView(withId(R.id.username_acct_profile))
                .check(matches(withText("avatar")));

        onView(withId(R.id.email_acct_textview))
                .check(matches(withText("Email")));

        onView(withId(R.id.email_acct_profile))
                .check(matches(withText("ruby@myemail.com")));

        onView(withId(R.id.age_acct_textview))
                .check(matches(withText("Age")));

        onView(withId(R.id.age_acct_profile))
                .check(matches(withText("24")));

        onView(withId(R.id.description_acct_textview))
                .check(matches(withText("Description")));

        onView(withId(R.id.description_acct_profile))
                .check(matches(withText("Commando, martial artist, and dance fighter.")));

        onView(withId(R.id.occupation_acct_textview))
                .check(matches(withText("Occupation")));

        onView(withId(R.id.occupation_acct_profile))
                .check(matches(withText("Martha's avatar")));

        //TestUtils.rotateScreen(activityTestRule.getActivity());
        TestUtils.rotateScreen(mActivityRule.getActivity());

        onView(withId(R.id.name_acct_textview))
                .check(matches(withText("Name")));

        onView(withId(R.id.name_acct_profile))
                .check(matches(withText("Ruby Roundhouse")));

        onView(withId(R.id.username_acct_textview))
                .check(matches(withText("User Name")));

        onView(withId(R.id.username_acct_profile))
                .check(matches(withText("avatar")));

        onView(withId(R.id.email_acct_textview))
                .check(matches(withText("Email")));

        onView(withId(R.id.email_acct_profile))
                .check(matches(withText("ruby@myemail.com")));

        onView(withId(R.id.age_acct_textview))
                .check(matches(withText("Age")));

        onView(withId(R.id.age_acct_profile))
                .check(matches(withText("24")));

        onView(withId(R.id.description_acct_textview))
                .check(matches(withText("Description")));

        onView(withId(R.id.description_acct_profile))
                .check(matches(withText("Commando, martial artist, and dance fighter.")));

        onView(withId(R.id.occupation_acct_textview))
                .check(matches(withText("Occupation")));

        onView(withId(R.id.occupation_acct_profile))
                .check(matches(withText("Martha's avatar")));


        onView(withId(R.id.matches_fragment)).perform(RecyclerViewActions
                .actionOnItem(allOf(hasDescendant(withText("Thanks for liking!"))), click()));
    }

    public static void setDate(int datePickerLaunchViewId, int year, int monthOfYear, int dayOfMonth) {
        onView(withId(datePickerLaunchViewId)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, monthOfYear, dayOfMonth));
        onView(withId(android.R.id.button1)).perform(click());
    }

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<MainActivity>(MainActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Intent testIntent = new Intent();
            testIntent.putExtra("Ruby Roundhouse", "Ruby Roundhouse");
            testIntent.putExtra("avatar", "avatar");
            testIntent.putExtra("ruby@myemail.com", "ruby@myemail.com");
            testIntent.putExtra("24", "24");
            testIntent.putExtra("Martha's avatar", "Martha's avatar");
            testIntent.putExtra("Commando, martial artist, and dance fighter.", "Commando, martial artist, and dance fighter.");

            return testIntent;
        }
    };

    @Test
    protected void testMatches(){
        
        onView(withId(R.id.matches_fragment)).perform(RecyclerViewActions
                .actionOnItem(allOf(hasDescendant(withText("MatchName"))), click()));

        onView(withId(R.id.recycler_view)
                .atPositionOnView(1, R.id.like_button))
                .perform(scrollTo(), click());
    }

}

