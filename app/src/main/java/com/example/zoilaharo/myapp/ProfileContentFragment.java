package com.example.zoilaharo.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

/**
 * Provides UI for the view with List.
 */
public class ProfileContentFragment extends Fragment {
    private EditText nameTxt, usernameTxt, ageTxt;
    private UserAccount.Operation operation;
    static final String KEY_TEXTVIEW_TEXT = "txtv_text";
    private int score;

    TextView username_View, name_View, email_View, age_View,occupation_View,description_View;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_profile, container, false);
        username_View = view.findViewById(R.id.username_acct_profile);
        name_View = view.findViewById(R.id.name_acct_profile);
        email_View = view.findViewById(R.id.email_acct_profile);
        age_View = view.findViewById(R.id.age_acct_profile);
        occupation_View = view.findViewById(R.id.occupation_acct_profile);
        description_View = view.findViewById(R.id.description_acct_profile);

        if (operation != null) {
            String name = operation.name;
            String username = operation.username;
            String email = operation.email;
            String age = operation.age;
            String occupation = operation.occupation;
            String description = operation.description;

            name_View.setText(name);
            username_View.setText(username);
            email_View.setText(email);
            age_View.setText(age);
            occupation_View.setText(occupation);
            description_View.setText(description);

        }

        if (savedInstanceState != null) {
            name_View.setText(savedInstanceState.getString("R.string.profFrag_name", getString(R.string.name)));
            username_View.setText(savedInstanceState.getString("R.string.profFrag_username", getString(R.string.username)));
            email_View.setText(savedInstanceState.getString("R.string.profFrag_email", getString(R.string.email)));
            age_View.setText(savedInstanceState.getString("R.string.profFrag_age", getString(R.string.age)));
            occupation_View.setText(savedInstanceState.getString("R.string.profFrag_occupation", getString(R.string.occupation)));
            description_View.setText(savedInstanceState.getString("R.string.profFrag_description", getString(R.string.description)));
        }

        return view;
    }
    public void setOperation(UserAccount.Operation operation) {
        this.operation = operation;
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("R.string.profFrag_name", name_View.getText().toString());
        outState.putString("R.string.profFrag_username", username_View.getText().toString());
        outState.putString("R.string.profFrag_email", email_View.getText().toString());
        outState.putString("R.string.profFrag_age", age_View.getText().toString());
        outState.putString("R.string.profFrag_description", description_View.getText().toString());
        outState.putString("R.string.profFrag_occupation", occupation_View.getText().toString());
    }

   }