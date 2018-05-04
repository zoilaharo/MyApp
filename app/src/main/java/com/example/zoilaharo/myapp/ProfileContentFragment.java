package com.example.zoilaharo.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.FragmentTransaction;

/**
 * Provides UI for the view with List.
 */
public class ProfileContentFragment extends Fragment {
    private EditText nameTxt, usernameTxt, ageTxt;
    private UserAccount.Operation operation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_profile, container, false);
        TextView username_View = view.findViewById(R.id.username_acct_profile);
        TextView name_View = view.findViewById(R.id.name_acct_profile);
        TextView email_View = view.findViewById(R.id.email_acct_profile);
        TextView age_View = view.findViewById(R.id.age_acct_profile);
        TextView occupation_View = view.findViewById(R.id.occupation_acct_profile);
       TextView description_View = view.findViewById(R.id.description_acct_profile);

        if (operation != null) {
            String name = operation.name;
            String username = operation.username;
            String email = operation.email;
            String age = operation.age;
            String occupation = operation.occupation;
            String description = operation.description;

            username_View.setText(username);
            name_View.setText(name);
            email_View.setText(email);
            age_View.setText(age);
            occupation_View.setText(occupation);
            description_View.setText(description);
        }

        return view;
    }
    public void setOperation(UserAccount.Operation operation) {
        this.operation = operation;
    }

   }