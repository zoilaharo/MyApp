package com.example.zoilaharo.myapp;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;

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
                .perform(typeText("butterfly"), closeSoftKeyboard());

//        onView(withId(R.id.birthday_edittext))
//                .perform(typeText("4-19-1990"), closeSoftKeyboard());

//        onView(withId(R.id.birthday_edittext)).perform(click());
//        onData(anything()).atPosition(1).perform(click());
//        onView(withId(R.id.custom_spinner)).check(matches(withSpinnerText(containsString("yourstring"))));
//        onView(withId(R.id.conf_info))
//                .check(matches(withText("@string/confmessage")));
    }

//    @Test
//    public void testOnClick() {
//
//        onView(withText("@string/signup")).perform(click());
//        intended(hasComponent(Confirmation.class.getName()));
//
//
//    }
}
