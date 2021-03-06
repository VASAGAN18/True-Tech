package com.example.splashscreen.common.LoginSignup;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.splashscreen.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Details extends AppCompatActivity {

    TextView fullname, username, email, password, gender, date, phonenumber, department, nationality;
    Button register;
    String FullName, Username, Email, Password, Gender, DOB, PhoneNo, Department, Nationality;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        register = findViewById(R.id.register_btn);

        fullname = findViewById(R.id.det_fullname);
        username = findViewById(R.id.det_username);
        email = findViewById(R.id.det_email);
        password = findViewById(R.id.det_password);
        gender = findViewById(R.id.det_gender);
        date = findViewById(R.id.det_dob);
        phonenumber = findViewById(R.id.det_phoneNumber);
        department = findViewById(R.id.det_department);
        nationality = findViewById(R.id.det_nationality);


        FullName = getIntent().getStringExtra("fullName");
        Username = getIntent().getStringExtra("username");
        Email = getIntent().getStringExtra("email");
        Password = getIntent().getStringExtra("password");
        Gender = getIntent().getStringExtra("radioGroup");
        DOB = getIntent().getStringExtra("month+\"/\"+ day +\"/\"+year");
        PhoneNo = getIntent().getStringExtra("phoneNo");
        Department = getIntent().getStringExtra("department");
        Nationality = getIntent().getStringExtra("nationality");


        fullname.setText(FullName);
        username.setText(Username);
        email.setText(Email);
        password.setText(Password);
        gender.setText(Gender);
        date.setText(DOB);
        phonenumber.setText(PhoneNo);
        nationality.setText(Nationality);
        department.setText(Department);


    }
    public void StoreData(View view) {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Users");

        UserHelperClass helperClass = new UserHelperClass(FullName, Username, Email, Password, Gender, DOB, PhoneNo, Department, Nationality);

        reference.child(Username).setValue(helperClass);



    }
}