package com.example.splashscreen.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.splashscreen.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class facultyprofile extends AppCompatActivity {

    String userEnteredUsername;
    TextView fac_name_profile,designation;
    Button pic_upload_btn;

    DatabaseReference dbfaculty;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultyprofile);
        fac_name_profile=findViewById(R.id.det_fullname);
        designation=findViewById(R.id.det_username);
        pic_upload_btn=findViewById(R.id.upload_btn);

        dbfaculty= FirebaseDatabase.getInstance().getReference("Faculty");


        final Intent intent=getIntent();
        userEnteredUsername=intent.getStringExtra("username");



        dbfaculty.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fac_name_profile.setText(dataSnapshot.child(userEnteredUsername).child("fullName").getValue(String.class).toUpperCase());
                designation.setText(dataSnapshot.child(userEnteredUsername).child("username").getValue(String.class).toUpperCase());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}