package com.example.zoilaharo.myapp;

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
import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.Toast;

import com.example.zoilaharo.myapp.Model.MatchesModel;
import com.example.zoilaharo.myapp.viewmodels.MatchesViewModel;


public class UserAccount extends AppCompatActivity implements  View.OnClickListener, MatchesContentFragment.OnListFragmentInteractionListener{
    String name, username, email, age, occupation, description;
    MatchesContentFragment matches = new MatchesContentFragment();
    public FragmentManager manager;
    private static final String TAG = UserAccount.class.getSimpleName();
    private MatchesViewModel viewModel;
    android.location.LocationManager locationManager;
    double longitudeNetwork, latitudeNetwork;
    android.widget.TextView longitudeValueNetwork, latitudeValueNetwork;

    TabLayout tabLayout;


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
      public class Mylocation {
        public double latitude;
        public double longitude;

        public Mylocation(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public double getLat(){
        return latitude;
        }

        public double getLong(){
        return longitude;
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
        SettingsContentFragment settings = new SettingsContentFragment();
        settings.setOperation(new Operation(name, username, age, email, occupation, description));
//        matches.setLocation(new Mylocation(longitudeNetwork, latitudeNetwork));        matches.setLocation(new Mylocation(longitudeNetwork, latitudeNetwork));

        adapter.addFragment(profile, "Profile");
        adapter.addFragment(matches, "Matches");
        adapter.addFragment(settings, "Settings");
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
    public void onClick(View v) {
        Toast.makeText(getApplicationContext(),R.string.liketoast,Toast.LENGTH_SHORT).show();
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


    public void addFragmentMatches(View view) {
        android.support.v4.app.Fragment fragmentMatches = new MatchesContentFragment();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container, fragmentMatches, "fragMatches");
        transaction.addToBackStack("AddFragMatches");
        transaction.commit();
    }

//    @Override
//    public void onBackPressed() {
//        if (manager.getBackStackEntryCount() > 0) {
//            manager.popBackStack();
//        } else {
//            super.onBackPressed();
//        }
//    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onListFragmentInteraction(MatchesModel item) {
        boolean liked = false;
        liked = item.liked;
        if (!liked) {
            liked = true;
        } else {
            liked = false;
        }
        viewModel.updateMatchesItem(item);
    }
}