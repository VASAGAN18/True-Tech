package com.example.splashscreen.Admin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.splashscreen.OwnerDashboard;
import com.example.splashscreen.R;
//import com.example.splashscreen.Server.ServerLogin;
import com.example.splashscreen.common.LoginSignup.SwitchWindow;
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
    RelativeLayout progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        adminUsername = findViewById(R.id.admin_userId);
        adminPassword = findViewById(R.id.admin_password);
        login = findViewById(R.id.AdminLogin);
        progressbar = findViewById(R.id.staff_progress_bar);
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

        if (!isConnected(this)){
            showCustomDialog();
        }

        if (!validateUsername() | !validatePassword()) {
            return;
        } else {
            isAdmin();
        }
        progressbar.setVisibility(View.VISIBLE);

    }



    private void isAdmin() {

        String userEnteredUsername = adminUsername.getEditText().getText().toString().trim();
        String userEnteredPassword = adminPassword.getEditText().getText().toString().trim();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Admin");
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);
        ((Query) checkUser).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {

                    adminUsername.setError(null);
                    adminUsername.setErrorEnabled(false);
                    adminUsername.requestFocus();

                    String passwordFromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    if (passwordFromDB.equals(userEnteredPassword)) {

                        adminPassword.setError(null);
                        adminPassword.setErrorEnabled(false);
                        adminPassword.requestFocus();



                        String fullNameFromDB = snapshot.child(userEnteredUsername).child("fullName").getValue(String.class);
                        String emailFromDB = snapshot.child(userEnteredUsername).child("email").getValue(String.class);
                        String genderFromDB = snapshot.child(userEnteredUsername).child("gender").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), OwnerDashboard.class);
                        intent.putExtra("username",userEnteredUsername);
                        finish();



                        intent.putExtra("fullName", fullNameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("gender", genderFromDB);
                        startActivity(intent);
                    } else {
                        progressbar.setVisibility(View.GONE);
                        adminPassword.setError("Wrong Password!");
                        adminPassword.requestFocus();
                    }
                } else {
                    progressbar.setVisibility(View.GONE);
                    adminUsername.setError("No such User exist");
                    adminUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressbar.setVisibility(View.GONE);

            }
        });
    }

    private boolean isConnected(AdminLogin adminLogin) {
        ConnectivityManager connectivityManager = (ConnectivityManager) adminLogin.getSystemService(Context.CONNECTIVITY_SERVICE);

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

        AlertDialog.Builder builder = new AlertDialog.Builder(AdminLogin.this);
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

//    public void callServerscreen(View view) {
//
//        Intent intent = new Intent(getApplicationContext(), ServerLogin.class);
//
//        Pair[] pairs = new Pair[1];
//        pairs[0] = new Pair<View, String>(findViewById(R.id.serverlogin), "transition_login");
//
//        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(AdminLogin.this, pairs);
//        startActivity(intent, options.toBundle());
//        finish();
//    }


}

