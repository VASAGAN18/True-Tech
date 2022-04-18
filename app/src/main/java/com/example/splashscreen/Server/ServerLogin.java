//package com.example.splashscreen.Server;
//
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.os.Bundle;
//import android.provider.Settings;
//import android.view.View;
//import android.widget.Button;
//import android.widget.RelativeLayout;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.splashscreen.R;
//import com.example.splashscreen.common.LoginSignup.SwitchWindow;
//import com.google.android.material.textfield.TextInputLayout;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;
//
//public class ServerLogin extends AppCompatActivity {
//    TextInputLayout serverUsername, serverPassword;
//    Button login;
//    RelativeLayout progressbar;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_server_login);
//        serverUsername = findViewById(R.id.server_userId);
//        serverPassword = findViewById(R.id.server_password);
//        login = findViewById(R.id.ServerLogin);
//        progressbar = findViewById(R.id.admin_progress_bar);
//    }
//
//    private boolean validatePassword() {
//        String val = serverPassword.getEditText().getText().toString().trim();
//
//
//        if (val.isEmpty()) {
//            serverPassword.setError("Field can not be empty");
//            return false;
//        } else {
//            serverPassword.setError(null);
//            serverPassword.setEnabled(false);
//            return true;
//        }
//    }
//
//    private boolean validateUsername() {
//        String val = serverUsername.getEditText().getText().toString().trim();
//
//        if (val.isEmpty()) {
//            serverUsername.setError("Field can not be empty");
//            return false;
//        } else {
//            serverUsername.setError(null);
//            serverUsername.setEnabled(false);
//            return true;
//        }
//
//    }
//
//    public void letTheAdminLogin(View view) {
//
//        if (!isConnected(this)){
//            showCustomDialog();
//        }
//
//        if (!validateUsername() | !validatePassword()) {
//            return;
//        } else {
//            isServer();
//        }
//        progressbar.setVisibility(View.VISIBLE);
//
//    }
//
//
//    private void isServer() {
//
//        String userEnteredUsername = serverUsername.getEditText().getText().toString().trim();
//        String userEnteredPassword = serverPassword.getEditText().getText().toString().trim();
//
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Admin");
//        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);
//        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                if (snapshot.exists()) {
//
//                    serverUsername.setError(null);
//                    serverUsername.setErrorEnabled(false);
//
//                    String passwordFromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);
//
//                    if (passwordFromDB.equals(userEnteredPassword)) {
//
//                        serverPassword.setError(null);
//                        serverPassword.setErrorEnabled(false);
//
//
//                        String fullNameFromDB = snapshot.child(userEnteredUsername).child("fullName").getValue(String.class);
//                        String emailFromDB = snapshot.child(userEnteredUsername).child("email").getValue(String.class);
//                        String genderFromDB = snapshot.child(userEnteredUsername).child("gender").getValue(String.class);
//
//                        Intent intent = new Intent(getApplicationContext(), ServerDashboard.class);
//                        intent.putExtra("username", userEnteredUsername);
//                        startActivity(intent);
//                    } else {
//                        progressbar.setVisibility(View.GONE);
//                        serverPassword.setError("Wrong Password!");
//                        serverPassword.requestFocus();
//                    }
//                } else {
//                    progressbar.setVisibility(View.GONE);
//                    serverUsername.setError("No such User exist");
//                    serverUsername.requestFocus();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                progressbar.setVisibility(View.GONE);
//
//            }
//        });
//    }
//
//    private boolean isConnected(ServerLogin serverLogin) {
//        ConnectivityManager connectivityManager = (ConnectivityManager) serverLogin.getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//
//        if ((wifiConn != null && wifiConn.isConnected()) || (mobileConn != null && mobileConn.isConnected())){
//            return true;
//        }
//        else{
//            return false;
//        }
//    }
//
//    private void showCustomDialog() {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(ServerLogin.this);
//        builder.setMessage("Please connect to the internet to proceed further")
//                .setCancelable(false)
//                .setPositiveButton("Connect",new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        startActivity(new Intent(Settings.ACTION_SETTINGS));
//                    }
//                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        startActivity(new Intent(getApplicationContext(), SwitchWindow.class));
//                        finish();
//                    }
//                });
//
//
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//
//
//    }
//
//}