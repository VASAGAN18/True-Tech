package com.example.splashscreen.common.LoginSignup;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.splashscreen.CustomerDash;
import com.example.splashscreen.R;
//import com.example.splashscreen.User.UserDashboard;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class  Login extends AppCompatActivity {

    TextInputLayout Username, Password;
    Button login;
    RelativeLayout progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Username = findViewById(R.id.login_userid);
        Password = findViewById(R.id.login_password);
        login = findViewById(R.id.UserLogin);
        progressbar = findViewById(R.id.user_progress_bar);
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

        if (!isConnected(this)){
            showCustomDialog();
        }

        if (!validateUsername() | !validatePassword()) {
            return;
        }
        else {
            isUser();
        }
        progressbar.setVisibility(View.VISIBLE);

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
                        String stateFromDB = snapshot.child(userEnteredUsername).child("state").getValue(String.class);
                        String nationalityFromDB = snapshot.child(userEnteredUsername).child("nationality").getValue(String.class);
                        String phoneNoFromDB = snapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), CustomerDash.class);
                        intent.putExtra("username",userEnteredUsername);
                        finish();
                        intent.putExtra("fullName",fullNameFromDB);
                        intent.putExtra("email",emailFromDB);
                        intent.putExtra("gender",genderFromDB);
                        intent.putExtra("dob",dobFromDB);
                        intent.putExtra("state",stateFromDB);
                        intent.putExtra("nationality",nationalityFromDB);
                        intent.putExtra("phoneNo",phoneNoFromDB);

                        startActivity(intent);
                    }
                    else{
                        progressbar.setVisibility(View.GONE);
                        Password.requestFocus();
                        Password.setError("Wrong Password!");

                    }
                }
                else {
                    progressbar.setVisibility(View.GONE);
                    Username.requestFocus();
                    Username.setError("No such User exist");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressbar.setVisibility(View.GONE);

            }
        });
    }
    private boolean isConnected(Login login) {

        ConnectivityManager connectivityManager = (ConnectivityManager) login.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if ((wifiConn != null && wifiConn.isConnected()) || (mobileConn != null && mobileConn.isConnected())){
            return true;
        }
        else{
            return false;
        }
    }

    private void showCustomDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
        builder.setMessage("Please connect to the internet to proceed further")
                .setCancelable(false)
                .setPositiveButton("Connect",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Settings.ACTION_SETTINGS));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(getApplicationContext(), SwitchWindow.class));
                        finish();
                    }
                });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();


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
