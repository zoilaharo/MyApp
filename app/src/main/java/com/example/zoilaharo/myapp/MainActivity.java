package com.example.zoilaharo.myapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
//import android.app.FragmentTransaction;
import android.app.Fragment;

import android.os.Bundle;
import android.text.InputType;

import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;



import java.util.Calendar;
import java.util.GregorianCalendar;


public class MainActivity extends Activity implements View.OnClickListener {
    private EditText name_edittext,username_edittext,email_edittext,birthday_edittext,age_edittext,occupation_edittext,description_edittext;
    private Button signup_button;
    private DatePickerDialog picker;
    private boolean passTestDate = true;
    public static final String EXTRA_MESSAGE = "com.example.zoilaharo.myapp.MESSAGE";
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Bind views with their ids
        bindViews();
        //Set listeners of view
        setViewActions();
        this.showDatePickerDialog();
    }

    private void bindViews(){
        username_edittext =findViewById(R.id.username_edittext);
        name_edittext = findViewById(R.id.name_edittext);
        email_edittext = findViewById(R.id.email_edittext);
        occupation_edittext = findViewById(R.id.occupation_edittext);
        description_edittext = findViewById(R.id.description_edittext);
        age_edittext = findViewById(R.id.age_edittext);
        birthday_edittext = findViewById((R.id.birthday_edittext));
        birthday_edittext.setInputType(InputType.TYPE_NULL);
        signup_button = (Button)findViewById(R.id.signup_button);
        signup_button.setOnClickListener(this);
    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void setViewActions() {
        signup_button.setOnClickListener(this);
        birthday_edittext.setInputType(InputType.TYPE_NULL);
        birthday_edittext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(passTestDate == true) {
           submitForm();
        }
        else
            Toast.makeText(getApplicationContext(),R.string.agetoast,Toast.LENGTH_SHORT).show();
    }


    private void submitForm(){
//        boolean a = TextUtils.isEmpty(username_edittext.getText());
//        boolean b = TextUtils.isEmpty(name_edittext.getText());
//        boolean c = TextUtils.isEmpty(age_edittext.getText());
//        boolean d = TextUtils.isEmpty(occupation_edittext.getText());
//        boolean e = TextUtils.isEmpty(description_edittext.getText());
//        boolean f = TextUtils.isEmpty(email_edittext.getText());
//
//        if (!a && !b && !c && !d && !e && !f) {
            Intent intent = new Intent (this, UserAccount.class);
            String username = username_edittext.getText().toString();
            String name = name_edittext.getText().toString();
            String email = email_edittext.getText().toString();
            String age = age_edittext.getText().toString();
            String occupation = occupation_edittext.getText().toString();
            String description = description_edittext.getText().toString();
            intent.putExtra("name_edittext", name);
            intent.putExtra("username_edittext", username);
            intent.putExtra("email_edittext", email);
            intent.putExtra("age_edittext", age);
            intent.putExtra("occupation_edittext", occupation);
            intent.putExtra("description_edittext", description);
            startActivity(intent);
//        }
    }

    // Create and show a DatePickerDialog when click button.
    private void showDatePickerDialog()
    {

        // Get open DatePickerDialog button.
        Button datePickerDialogButton = (Button)findViewById(R.id.datePickerDialogButton);
        datePickerDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passTestDate = true;
                // Create a new OnDateSetListener instance. This listener will be invoked when user click ok button in DatePickerDialog.
                DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        boolean failpass = true;
                        StringBuffer strBuf = new StringBuffer();
                        strBuf.append(month+1);
                        strBuf.append("-");
                        strBuf.append(dayOfMonth);
                        strBuf.append("-");
                        strBuf.append(year);

                        //Enter date choosen on DOB field

                        birthday_edittext.setText(strBuf.toString());

                        //Today's date minus date entered displayed on age field
                        android.icu.util.Calendar now = android.icu.util.Calendar.getInstance();
                        int thisyear = now.get(java.util.Calendar.YEAR);
                        int diff = thisyear - year;
                        TextView agetext = (TextView) findViewById( R.id.age_edittext );
                        agetext.setText(Integer.toString(diff));

                        //Used to check if user is older than 18
                        android.icu.util.Calendar userAge = new android.icu.util.GregorianCalendar(year,month,dayOfMonth);
                        android.icu.util.Calendar minAdultAge = new android.icu.util.GregorianCalendar();

                        minAdultAge.add(android.icu.util.Calendar.YEAR, -18);
                        if (minAdultAge.before(userAge)) {
                            Toast.makeText(getApplicationContext(),R.string.agetoast,Toast.LENGTH_SHORT).show();
                            passTestDate = false;
                        }
                        if(year > thisyear)
                        {
                            Toast.makeText(getApplicationContext(),R.string.yeartoast,Toast.LENGTH_SHORT).show();
                            passTestDate = false;
                        }


                    }
                };

                // Get current year, month and day.
                android.icu.util.Calendar now = android.icu.util.Calendar.getInstance();
                int year = now.get(java.util.Calendar.YEAR);
                int month = now.get(java.util.Calendar.MONTH);
                int day = now.get(java.util.Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Dialog, onDateSetListener, year, month, day);
                // Set dialog icon and title.
                datePickerDialog.setTitle(R.string.select);

                // Popup the dialog.
                datePickerDialog.show();
            }
        });
    }
    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

}