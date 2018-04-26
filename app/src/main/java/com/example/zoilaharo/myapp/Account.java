package com.example.zoilaharo.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

public class Account extends Activity {

    private Button goBack;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        // Get the Intent that started this activity and extract the string
//        Intent intent = getIntent();
//        Bundle bundle = intent.getBundleExtra("info");
//
//        String username = bundle.getString("username_edittext");
//        String name = bundle.getString("name_edittext");
//        String age = bundle.getString("age_edittext");
//
//        EditText username_View = findViewById(R.id.username_acct);
//        username_View.setText(username);
//
//        EditText name_View = findViewById(R.id.name_acct);
//        name_View.setText(name);
//
//        EditText age_View = findViewById(R.id.age_acct);
//        age_View.setText(age);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String name = bundle.getString("name_edittext");
        String username = bundle.getString("username_edittext");
        String age = bundle.getString("age_edittext");

        EditText username_View = findViewById(R.id.username_acct);
        username_View.setText(username);

        EditText name_View = findViewById(R.id.name_acct);
        name_View.setText(name);

        EditText age_View = findViewById(R.id.age_acct);
        age_View.setText(age);

        goBack = findViewById(R.id.bt_go_back);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });
    }

}