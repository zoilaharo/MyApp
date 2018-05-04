package com.example.zoilaharo.myapp;

import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;


public class UserAccount extends AppCompatActivity {
    String name, username, email, age, occupation, description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useraccount);

        // Adding Toolbar to Main screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Setting ViewPager for each Tabs
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        // Set Tabs inside Toolbar
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);



    }

    public class Operation {
        public String name;
        public String username;
        public String email;
        public String age;
        public String occupation;
        public String description;

        public Operation(String name, String username, String age, String email,String occupation, String description) {
            this.name = name;
            this.username = username;
            this.email = email;
            this.age = age;
            this.occupation = occupation;
            this.description = description;
        }
    }
    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        name = bundle.getString("name_edittext");
        username = bundle.getString("username_edittext");
        email = bundle.getString("email_edittext");
        age = bundle.getString("age_edittext");
        occupation = bundle.getString("occupation_edittext");
        description = bundle.getString("description_edittext");

        ProfileContentFragment profile = new ProfileContentFragment();
        profile.setOperation(new Operation(name, username, age, email, occupation, description));
        adapter.addFragment(profile, "Profile");
        adapter.addFragment(new MatchesContentFragment(), "Matches");
        adapter.addFragment(new SettingsContentFragment(), "Settings");
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_goback) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

