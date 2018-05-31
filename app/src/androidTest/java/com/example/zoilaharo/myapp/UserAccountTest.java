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
import android.support.test.espresso.Espresso;
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
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class UserAccountTest {
    @Rule
    public ActivityTestRule<UserAccount> activityTestRule
            = new ActivityTestRule<UserAccount>(UserAccount.class){
        @Override
        protected Intent getActivityIntent() {
            Intent testIntent = new Intent();
            testIntent.putExtra("name_edittext", "Ruby Roundhouse");
            testIntent.putExtra("username_edittext", "avatar");
            testIntent.putExtra("email_edittext", "ruby@myemail.com");
            testIntent.putExtra("age_edittext", "24");
            testIntent.putExtra("occupation_edittext", "Martha's avatar");
            testIntent.putExtra("description_edittext", "Commando, martial artist, and dance fighter.");

            return testIntent;
        }
    };

    @Test
    public void testform(){
        onView(withId(R.id.profileImg))
                .check(matches(isDisplayed()));

        onView(withId(R.id.name_acct_textview))
                .check(matches(withText("Name:")));

        onView(withId(R.id.name_acct_profile))
                .check(matches(withText("Ruby Roundhouse")));

        onView(withId(R.id.username_acct_textview))
                .check(matches(withText("User Name:")));

        onView(withId(R.id.username_acct_profile))
                .check(matches(withText("avatar")));

        onView(withId(R.id.email_acct_textview))
                .check(matches(withText("Email:")));

        onView(withId(R.id.email_acct_profile))
                .check(matches(withText("ruby@myemail.com")));

        onView(withId(R.id.age_acct_textview))
                .check(matches(withText("Age:")));

        onView(withId(R.id.age_acct_profile))
                .check(matches(withText("24")));

        onView(withId(R.id.description_acct_textview))
                .check(matches(withText("Description:")));

        onView(withId(R.id.description_acct_profile))
                .check(matches(withText("Commando, martial artist, and dance fighter.")));

        onView(withId(R.id.occupation_acct_textview))
                .check(matches(withText("Occupation:")));

        onView(withId(R.id.occupation_acct_profile))
                .check(matches(withText("Martha's avatar")));

        //TestUtils.rotateScreen(activityTestRule.getActivity());
        TestUtils.rotateScreen(activityTestRule.getActivity());

        onView(withId(R.id.profileImg))
                .check(matches(isDisplayed()));

        onView(withId(R.id.name_acct_textview))
                .check(matches(withText("Name:")));

        onView(withId(R.id.name_acct_profile))
                .check(matches(withText("Ruby Roundhouse")));

        onView(withId(R.id.username_acct_textview))
                .check(matches(withText("User Name:")));

        onView(withId(R.id.username_acct_profile))
                .check(matches(withText("avatar")));

        onView(withId(R.id.email_acct_textview))
                .check(matches(withText("Email:")));

        onView(withId(R.id.email_acct_profile))
                .check(matches(withText("ruby@myemail.com")));

        onView(withId(R.id.age_acct_textview))
                .check(matches(withText("Age:")));

        onView(withId(R.id.age_acct_profile))
                .check(matches(withText("24")));

        onView(withId(R.id.description_acct_textview))
                .check(matches(withText("Description:")));

        onView(withId(R.id.description_acct_profile))
                .check(matches(withText("Commando, martial artist, and dance fighter.")));

        onView(withId(R.id.occupation_acct_textview))
                .check(matches(withText("Occupation:")));

        onView(withId(R.id.occupation_acct_profile))
                .check(matches(withText("Martha's avatar")));

    }

    @Test
    public void checkTabLayoutDisplayed() {
        onView(withId(R.id.viewpager))
                .perform(click())
                .check(matches(isDisplayed()));
    }

    @Test
    public void swipeThroughTebs(){
        onView(withId(R.id.profileImg))
                .check(matches(isDisplayed()));

        onView(withId(R.id.name_acct_textview))
                .check(matches(withText("Name:")));

        onView(withId(R.id.name_acct_profile))
                .check(matches(withText("Ruby Roundhouse")));

        onView(withId(R.id.username_acct_textview))
                .check(matches(withText("User Name:")));

        onView(withId(R.id.username_acct_profile))
                .check(matches(withText("avatar")));

        onView(withId(R.id.email_acct_textview))
                .check(matches(withText("Email:")));

        onView(withId(R.id.email_acct_profile))
                .check(matches(withText("ruby@myemail.com")));

        onView(withId(R.id.age_acct_textview))
                .check(matches(withText("Age:")));

        onView(withId(R.id.age_acct_profile))
                .check(matches(withText("24")));

        onView(withId(R.id.description_acct_textview))
                .check(matches(withText("Description:")));

        onView(withId(R.id.description_acct_profile))
                .check(matches(withText("Commando, martial artist, and dance fighter.")));

        onView(withId(R.id.occupation_acct_textview))
                .check(matches(withText("Occupation:")));

        onView(withId(R.id.occupation_acct_profile))
                .check(matches(withText("Martha's avatar")));

        onView(withId(R.id.viewpager))
                .perform(swipeLeft());

        onView(withId(R.id.viewpager))
                .perform(swipeLeft());

        //Settings tab
            onView(withId(R.id.name_edittext_settings))
                    .perform(typeText("Ruby Roundhouse"), closeSoftKeyboard());

            onView(withId(R.id.email_edittext_settings))
                    .perform(typeText("ruby@myemail.com"), closeSoftKeyboard());

            onView(withId(R.id.remindertime_edittext_settings))
                    .perform(typeText("10:00am"), closeSoftKeyboard());

            onView(withId(R.id.maxDistance_edittext_settings))
                    .perform(typeText("10 miles"), closeSoftKeyboard());

            onView(withId(R.id.gender_edittext_settings))
                    .perform(typeText("Female"), closeSoftKeyboard());

            onView(withId(R.id.status_edittext_settings))
                    .perform(typeText("Single"), closeSoftKeyboard());

            onView(withId(R.id.agemin_edittext_settings))
                    .perform(typeText("30"), closeSoftKeyboard());

            onView(withId(R.id.agemax_edittext_settings))
                    .perform(typeText("40"), closeSoftKeyboard());

    }

    @Test
    public void testLikes() {
        UserAccount activity = activityTestRule.getActivity();

        //swipe to matches tab
        onView(withId(R.id.viewpager))
                .perform(swipeLeft());

        //click like button
        onView(withId(R.id.my_recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, TestUtils.clickChildViewWithId(R.id.like_button)));

        onView(withId(R.id.my_recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(1, TestUtils.clickChildViewWithId(R.id.like_button)));

        onView(withId(R.id.my_recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(2, TestUtils.clickChildViewWithId(R.id.like_button)));

        onView(withId(R.id.my_recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(3, TestUtils.clickChildViewWithId(R.id.like_button)));

        onView(withId(R.id.my_recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(4, TestUtils.clickChildViewWithId(R.id.like_button)));

        onView(withId(R.id.my_recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(5, TestUtils.clickChildViewWithId(R.id.like_button)));
    }



}

//    @Test
//    public void testBackButton() {
//        Espresso.pressBack();
//
//        onView(withId(R.id.name_edittext))
//                .check(matches(withText("")));
//
//        onView(withId(R.id.email_edittext))
//                .check(matches(withText("")));
//
//        onView(withId(R.id.username_edittext))
//                .check(matches(withText("")));
//
//        onView(withId(R.id.description_edittext))
//                .check(matches(withText("")));
//
//        onView(withId(R.id.birthday_edittext))
//                .check(matches(withText("")));
//        onView(withId(R.id.age_edittext))
//                .check(matches(withText("")));
//    }

