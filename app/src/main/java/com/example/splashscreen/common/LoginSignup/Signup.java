package com.example.splashscreen.common.LoginSignup;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.splashscreen.R;
import com.google.android.material.textfield.TextInputLayout;

public class Signup extends AppCompatActivity {

    //Variables

    Button next, login;
    TextView titleText;

    //Get data variables
    TextInputLayout fullName, username, email, password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Hooks for animation

        next = findViewById(R.id.signup_next_button);
        login = findViewById(R.id.signup_login_button);
        titleText = findViewById(R.id.signup_title_text);

        //hooks for getting data
        fullName = findViewById(R.id.signup_fullname);
        username = findViewById(R.id.signup_username);
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);
    }

    public void callLoginScreen(View view) {


        Intent intent = new Intent(getApplicationContext(), Login.class);

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.signup_login_button), "transition_login");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Signup.this, pairs);
        startActivity(intent, options.toBundle());
    }


    public void callNextSignupScreen(View view) {
        if(!validateFullname() | !validateUsername() | !validateEmail() | !validatePassword()){
            return;
        }

        String _fullName = fullName.getEditText().getText().toString();
        String _username = username.getEditText().getText().toString();
        String _email = email.getEditText().getText().toString();
        String _password = password.getEditText().getText().toString();

        Intent intent = new Intent(getApplicationContext(), Signup2ndClass.class);

        intent.putExtra("fullName",_fullName);
        intent.putExtra("email",_email);
        intent.putExtra("username",_username);
        intent.putExtra("password",_password);


        //Add Transition
        Pair[] pairs = new Pair[3];
        pairs[0] = new Pair<View, String>(next, "transition_next_btn");
        pairs[1] = new Pair<View, String>(login, "transition_login_btn");
        pairs[2] = new Pair<View, String>(titleText, "transition_title_text");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Signup.this, pairs);
        startActivity(intent, options.toBundle());
        finish();


    }

    private boolean validateFullname() {
        String val = fullName.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            fullName.setError("Field can not be empty!");
            return false;
        } else {
            fullName.setError(null);
            fullName.setEnabled(false);
            return true;
        }

    }

    private boolean validateUsername() {
        String val = username.getEditText().getText().toString().trim();
        String checkspaces = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            username.setError("Field can not be empty");
            return false;
        } else if (val.length() > 20) {
            username.setError("Username is too large!");
            return false;
        } else if (!val.matches(checkspaces)) {
            username.setError("No white spaces are allowed!");
            return false;
        } else {
            username.setError(null);
            username.setEnabled(false);
            return true;
        }

    }

    private boolean validateEmail() {
        String val = email.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


        if (val.isEmpty()) {
            email.setError("Field can not be empty");
            return false;
        }
        else if (!val.matches(checkEmail)) {
            email.setError("Invalid Email!");
            return false;
        } else {
            email.setError(null);
            email.setEnabled(false);
            return true;
        }

    }

    private boolean validatePassword() {
        String val = password.getEditText().getText().toString().trim();
        String checkPassword = "^" +
                "(?=.*[0-9])" +         //at least one digit
                "(?=.*[a-z])" +         //at least one lower case letter
                "(?=.*[A-Z])" +         //at least one upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=\\S+$)" +          //no white spaces
                ".{4,}" +              //at least 4 characters
                "$";


        if (val.isEmpty()) {
            password.setError("Field can not be empty");
            return false;
        }
        else if (!val.matches(checkPassword)) {
            password.setError("Password should contain atleast 4 characters with 1 lowercase and 1 uppercase chracter and atleast one digit");
            return false;
        } else {
            password.setError(null);
            password.setEnabled(false);
            return true;
        }

    }



    }





