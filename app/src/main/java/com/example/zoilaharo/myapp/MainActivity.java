package com.example.zoilaharo.myapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;



import java.util.Calendar;
import java.util.GregorianCalendar;


public class MainActivity extends Activity implements View.OnClickListener {
    private EditText name_edittext,username_edittext,email_edittext,birthday_edittext;
    private Button signup_button;
    private DatePickerDialog picker;
    private boolean passTestDate = true;
    public static final String EXTRA_MESSAGE = "com.example.zoilaharo.myapp.MESSAGE";


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
        birthday_edittext = findViewById((R.id.birthday_edittext));
        birthday_edittext.setInputType(InputType.TYPE_NULL);
        signup_button = (Button)findViewById(R.id.signup_button);
        signup_button.setOnClickListener(this);
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
            Toast.makeText(getApplicationContext(),"You must be 18 or older.",Toast.LENGTH_SHORT).show();


    }

    private void submitForm(){
        Intent startNewActivity = new Intent (this, Confirmation.class);
        EditText editText = (EditText) findViewById(R.id.username_edittext);
        String message = editText.getText().toString();
        startNewActivity.putExtra(EXTRA_MESSAGE, message);
        startActivity(startNewActivity);
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
                        EditText editText = (EditText) findViewById( R.id.birthday_edittext );
                        editText.setText(strBuf.toString());

                        //Today's date minus date entered displayed on age field
                        android.icu.util.Calendar now = android.icu.util.Calendar.getInstance();
                        int thisyear = now.get(java.util.Calendar.YEAR);
                        int diff = thisyear - year;
                        TextView agetext = (TextView) findViewById( R.id.age_textview );
                        agetext.setText(Integer.toString(diff));

                        //Used to check if user is older than 18
                        android.icu.util.Calendar userAge = new android.icu.util.GregorianCalendar(year,month,dayOfMonth);
                        android.icu.util.Calendar minAdultAge = new android.icu.util.GregorianCalendar();

                        minAdultAge.add(android.icu.util.Calendar.YEAR, -18);
                        if (minAdultAge.before(userAge)) {
                            Toast.makeText(getApplicationContext(),"You must be 18 or older.",Toast.LENGTH_SHORT).show();
                            passTestDate = false;
                        }
                        if(year > thisyear)
                        {
                            Toast.makeText(getApplicationContext(),"Year must be greater than current year.",Toast.LENGTH_SHORT).show();
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
                datePickerDialog.setTitle("Please select date.");

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
