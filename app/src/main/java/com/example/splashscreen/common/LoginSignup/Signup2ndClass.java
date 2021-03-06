package com.example.splashscreen.common.LoginSignup;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.splashscreen.R;

public class Signup2ndClass extends AppCompatActivity {


    //Variables

    Button next;
    TextView titleText;
    RadioGroup radioGroup;
    RadioButton selectedGender;
    DatePicker datePicker;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2nd_class);


        //Hooks

        next = findViewById(R.id.signup_next_button);
        titleText = findViewById(R.id.signup_title_text);

        radioGroup = findViewById(R.id.radio_group);
        selectedGender = findViewById(radioGroup.getCheckedRadioButtonId());
        datePicker = findViewById(R.id.dob_picker);


    }


    public void callNextSignupScreen(View view) {
        if (!validateGender()) {
            return;
        }
        selectedGender = findViewById(radioGroup.getCheckedRadioButtonId());
        String _gender = selectedGender.getText().toString();

        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        String _DOB = month+"/"+day+"/"+year;



        String _fullName = getIntent().getStringExtra("fullName");
        String _username = getIntent().getStringExtra("username");
        String _email = getIntent().getStringExtra("email");
        String _password = getIntent().getStringExtra("password");



        Intent intent = new Intent(getApplicationContext(), Signup3rdClass.class);

        intent.putExtra("fullName",_fullName);
        intent.putExtra("email",_email);
        intent.putExtra("username",_username);
        intent.putExtra("password",_password);
        intent.putExtra("radioGroup",_gender);
        intent.putExtra("month+\"/\"+ day +\"/\"+year", _DOB);


        //Add Transition
        Pair[] pairs = new Pair[2];


        pairs[0] = new Pair<View, String>(next, "transition_next_btn");
        pairs[1] = new Pair<View, String>(titleText, "transition_title_text");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Signup2ndClass.this, pairs);
        startActivity(intent, options.toBundle());
        finish();
    }

    private boolean validateGender() {
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please Select Gender", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            radioGroup.setEnabled(false);
            return true;
        }
    }


}