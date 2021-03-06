package com.example.splashscreen.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.splashscreen.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AdminLogin extends AppCompatActivity {
    TextInputLayout adminUsername, adminPassword;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        adminUsername = findViewById(R.id.admin_userId);
        adminPassword = findViewById(R.id.admin_password);
        login = findViewById(R.id.AdminLogin);
    }

    private boolean validatePassword() {
        String val = adminPassword.getEditText().getText().toString().trim();


        if (val.isEmpty()) {
            adminPassword.setError("Field can not be empty");
            return false;
        } else {
            adminPassword.setError(null);
            adminPassword.setEnabled(false);
            return true;
        }
    }

    private boolean validateUsername() {
        String val = adminUsername.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            adminUsername.setError("Field can not be empty");
            return false;
        } else {
            adminUsername.setError(null);
            adminUsername.setEnabled(false);
            return true;
        }

    }

    public void letTheAdminLogin(View view) {
        if (!validateUsername() | !validatePassword()) {
            return;
        } else {
            isAdmin();
        }

    }

    private void isAdmin() {

        String userEnteredUsername = adminUsername.getEditText().getText().toString().trim();
        String userEnteredPassword = adminPassword.getEditText().getText().toString().trim();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Admin");
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {

                    adminUsername.setError(null);
                    adminUsername.setErrorEnabled(false);

                    String passwordFromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    if (passwordFromDB.equals(userEnteredPassword)) {

                        adminPassword.setError(null);
                        adminPassword.setErrorEnabled(false);


                        String fullNameFromDB = snapshot.child(userEnteredUsername).child("fullName").getValue(String.class);
                        String emailFromDB = snapshot.child(userEnteredUsername).child("email").getValue(String.class);
                        String genderFromDB = snapshot.child(userEnteredUsername).child("gender").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), AdminDashboard.class);


                        intent.putExtra("fullName",fullNameFromDB);
                        intent.putExtra("email",emailFromDB);
                        intent.putExtra("gender",genderFromDB);
                        startActivity(intent);
                    } else {
                        adminPassword.setError("Wrong Password!");
                        adminPassword.requestFocus();
                    }
                } else {
                    adminUsername.setError("No such User exist");
                    adminUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}