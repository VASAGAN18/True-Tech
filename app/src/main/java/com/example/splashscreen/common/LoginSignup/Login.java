package com.example.splashscreen.common.LoginSignup;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.splashscreen.R;
import com.example.splashscreen.User.UserDashboard;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    TextInputLayout Username, Password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Username = findViewById(R.id.login_userid);
        Password = findViewById(R.id.login_password);
        login = findViewById(R.id.UserLogin);
    }

    private boolean validatePassword() {
        String val = Password.getEditText().getText().toString().trim();


        if (val.isEmpty()) {
            Password.setError("Field can not be empty");
            return false;
        } else {
            Password.setError(null);
            Password.setEnabled(false);
            return true;
        }
    }

    private boolean validateUsername() {
        String val = Username.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            Username.setError("Field can not be empty");
            return false;
        } else {
            Username.setError(null);
            Username.setEnabled(false);
            return true;
        }

    }

    public void letTheUserLogin(View view) {
        if (!validateUsername() | !validatePassword()) {
            return;
        }
        else {
            isUser();
        }

    }

    private void isUser() {

        String userEnteredUsername = Username.getEditText().getText().toString().trim();
        String userEnteredPassword = Password.getEditText().getText().toString().trim();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    Username.setError(null);
                    Username.setErrorEnabled(false);

                    String passwordFromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    if(passwordFromDB.equals(userEnteredPassword)){

                        Password.setError(null);
                        Password.setErrorEnabled(false);

                        String fullNameFromDB = snapshot.child(userEnteredUsername).child("fullName").getValue(String.class);
                        String emailFromDB = snapshot.child(userEnteredUsername).child("email").getValue(String.class);
                        String genderFromDB = snapshot.child(userEnteredUsername).child("gender").getValue(String.class);
                        String dobFromDB = snapshot.child(userEnteredUsername).child("dob").getValue(String.class);
                        String departmentFromDB = snapshot.child(userEnteredUsername).child("department").getValue(String.class);
                        String nationalityFromDB = snapshot.child(userEnteredUsername).child("nationality").getValue(String.class);
                        String phoneNoFromDB = snapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), UserDashboard.class);

                        intent.putExtra("fullName",fullNameFromDB);
                        intent.putExtra("email",emailFromDB);
                        intent.putExtra("gender",genderFromDB);
                        intent.putExtra("dob",dobFromDB);
                        intent.putExtra("department",departmentFromDB);
                        intent.putExtra("nationality",nationalityFromDB);
                        intent.putExtra("phoneNo",phoneNoFromDB);

                        startActivity(intent);
                    }
                    else{
                        Password.setError("Wrong Password!");
                        Password.requestFocus();
                    }
                }
                else {
                    Username.setError("No such User exist");
                    Username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void callSignupscreen(View view) {

        Intent intent = new Intent(getApplicationContext(), Signup.class);

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.CreateAccount), "transition_login");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
        startActivity(intent, options.toBundle());
        finish();
    }


}
