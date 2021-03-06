package com.example.splashscreen.common.LoginSignup;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.splashscreen.R;
import com.google.android.material.textfield.TextInputLayout;

public class Signup3rdClass extends AppCompatActivity {


    Button next;
    TextInputLayout phoneNumber, department, nationality;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup3rd_class);


        next = findViewById(R.id.next_btn);
        phoneNumber = findViewById(R.id.phone_number);
        department = findViewById(R.id.department);
        nationality = findViewById(R.id.nationality);


    }


    public void callDetailsScreen(View view) {

        if (!validatePhoneNumber() | !validateDepartment() | !validateNationality()) {
            return;
        }


        String _fullName = getIntent().getStringExtra("fullName");
        String _username = getIntent().getStringExtra("username");
        String _email = getIntent().getStringExtra("email");
        String _password = getIntent().getStringExtra("password");
        String _gender = getIntent().getStringExtra("radioGroup");
        String _DOB = getIntent().getStringExtra("month+\"/\"+ day +\"/\"+year");

        String _phoneNumber = phoneNumber.getEditText().getText().toString().trim();
        String _phoneNo = "+91" + _phoneNumber;
        String _nationality = nationality.getEditText().getText().toString();
        String _department = department.getEditText().getText().toString();


        Intent intent = new Intent(getApplicationContext(), Details.class);

        intent.putExtra("fullName", _fullName);
        intent.putExtra("email", _email);
        intent.putExtra("username", _username);
        intent.putExtra("password", _password);
        intent.putExtra("radioGroup", _gender);
        intent.putExtra("phoneNo", _phoneNo);
        intent.putExtra("nationality", _nationality);
        intent.putExtra("department", _department);
        intent.putExtra("month+\"/\"+ day +\"/\"+year", _DOB);


        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.next_btn), "transition_login");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Signup3rdClass.this, pairs);
        startActivity(intent, options.toBundle());
        finish();


    }


    private boolean validatePhoneNumber() {
        String val = phoneNumber.getEditText().getText().toString().trim();
        String checkspaces = "\\d{10}$";
        if (val.isEmpty()) {
            phoneNumber.setError("Enter Phone number");
            return false;
        } else if (!val.matches(checkspaces)) {
            phoneNumber.setError("Enter valid Phone number");
            return false;
        } else {
            phoneNumber.setError(null);
            phoneNumber.setEnabled(false);
            return true;
        }
    }

    private boolean validateDepartment() {
        String val = department.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            department.setError("Field can not be empty!");
            return false;
        } else {
            department.setError(null);
            department.setEnabled(false);
            return true;
        }

    }

    private boolean validateNationality() {
        String val = nationality.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            nationality.setError("Field can not be empty!");
            return false;
        } else {
            nationality.setError(null);
            nationality.setEnabled(false);
            return true;
        }
    }


}



