package com.example.zoilaharo.myapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.example.zoilaharo.myapp.Entity.User;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Provides UI for the view with List.
 */
public class SettingsContentFragment extends Fragment {
    private UserAccount.Operation operation;
    EditText name_View, email_View, reminderTime_View, maxDistance_View, status_View, gender_View, minAge_View, maxAge_View;
    Button update_button;
    String name, email, reminderTime, maxDistance, gender, privacy, minAge, maxAge;
    public static final int LENGTH_SHORT = 0;
    List<User> users = null;
    User user = new User();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_settings, container, false);
        name_View = view.findViewById(R.id.name_edittext_settings);
        email_View = view.findViewById(R.id.email_edittext_settings);
        update_button = view.findViewById(R.id.update);
        reminderTime_View = view.findViewById(R.id.remindertime_edittext_settings);
        maxDistance_View = view.findViewById(R.id.maxDistance_edittext_settings);
        gender_View = view.findViewById(R.id.gender_edittext_settings);
        status_View = view.findViewById(R.id.status_edittext_settings);
        minAge_View = view.findViewById(R.id.agemin_edittext_settings);
        maxAge_View = view.findViewById(R.id.agemax_edittext_settings);


        if (operation != null) {

            name = operation.name;
            email = operation.email;
            name_View.setText(name);
            email_View.setText(email);

        }

        if (savedInstanceState != null) {
            name_View.setText(savedInstanceState.getString("R.string.profileFrag_name", getString(R.string.name)));
            email_View.setText(savedInstanceState.getString("R.string.profileFrag_email", getString(R.string.email)));

        }

        update_button.setOnClickListener(v -> {

            if (users == null || users.isEmpty()) {
                user = new User();
            } else {
                user = users.get(0);
            }
            user.setEmail(email_View.getText().toString());
            user.setFirstName(name_View.getText().toString());
            user.setReminderTime(reminderTime_View.getText().toString());
            user.setMaxDistance(maxDistance_View.getText().toString());
            user.setStatus(status_View.getText().toString());
            user.setGender(gender_View.getText().toString());
            user.setAgeMin(Integer.parseInt(minAge_View.getText().toString()));
            user.setAgeMin(Integer.parseInt(maxAge_View.getText().toString()));

            Context context = getContext();
            int duration = Toast.LENGTH_SHORT;
            String text = getString(R.string.update);

            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            updateSetting(user);

        });

        new GetUserTask(this, email).execute();
        return view;
    }

    public void updateSetting(User user){
        new UpdateUserTask(this, user).execute();
    }
    public void setOperation(UserAccount.Operation operation) {
        this.operation = operation;
    }
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putString("R.string.profFrag_name", name_View.getText().toString());
//        outState.putString("R.string.profFrag_email", email_View.getText().toString());
//
//    }

    private class GetUserTask extends AsyncTask<Void, Void, User> {

        private WeakReference<Fragment> weakFragment;
        private String email;

        public GetUserTask(android.support.v4.app.Fragment fragment, String userEmail) {
            weakFragment = new WeakReference<>(fragment);
            this.email = userEmail;
        }

        @Override
        protected User doInBackground(Void... voids) {
            Fragment fragment = weakFragment.get();
            if (fragment == null) {
                return null;
            }

            AppDatabase db = AppDatabaseSingleton.getDatabase(fragment.getActivity().getApplicationContext());
            String[] emails = { email };

            users = db.userDao().getAll();

            if(users.size() <= 0 || users.get(0) == null) {
                return null;
            }
            return user;
        }

        @Override
        protected void onPostExecute(User user) {
            SettingsContentFragment fragment = (SettingsContentFragment) weakFragment.get();
            if (user == null || fragment == null) {
                return;
            }

            reminderTime_View.setText(user.getReminderTime());
            maxDistance_View.setText(user.getMaxDistance());
            status_View.setText(user.getStatus());
            gender_View.setText(user.getGender());
            minAge_View.setText(String.valueOf(user.getAgeMin()));
            maxAge_View.setText(String.valueOf(user.getAgeMax()));
        }
    }

    private class UpdateUserTask extends AsyncTask<Void, Void, User> {

        private WeakReference<android.support.v4.app.Fragment> weakFragment;
        private User user;

        public UpdateUserTask(android.support.v4.app.Fragment fragment, User user) {
            weakFragment = new WeakReference<>(fragment);
            this.user = user;
        }

        @Override
        protected User doInBackground(Void... voids) {
            android.support.v4.app.Fragment fragment = weakFragment.get();
            if(fragment == null) {
                return null;
            }

//            AppDatabase db = AppDatabaseSingleton.getDatabase(fragment.getActivity());
            AppDatabase db = AppDatabaseSingleton.getDatabase(fragment.getContext());

            db.userDao().insertAll(user);
            return user;
        }
        @Override
        protected void onPostExecute(User user) {
            SettingsContentFragment fragment = (SettingsContentFragment) weakFragment.get();
            if (user == null || fragment == null) {
                return;
            }
            reminderTime_View.setText(user.getReminderTime());
            maxDistance_View.setText(user.getMaxDistance());
            status_View.setText(user.getStatus());
            gender_View.setText(user.getGender());
            minAge_View.setText(String.valueOf(user.getAgeMin()));
            maxAge_View.setText(String.valueOf(user.getAgeMax()));
        }
    }
}