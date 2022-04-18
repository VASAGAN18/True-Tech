package com.example.splashscreen.common.LoginSignup;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.splashscreen.Admin.AdminLogin;
import com.example.splashscreen.R;

public class SwitchWindow extends AppCompatActivity {

    Button teacher, student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_window);

        teacher = findViewById(R.id.Admin_login);
        student = findViewById(R.id.User_login);
    }

    public void callWelcomeScreen(View view) {

        Intent intent = new Intent(getApplicationContext(), WelcomeScreen.class);

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.User_login), "transition_login");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SwitchWindow.this, pairs);
        startActivity(intent, options.toBundle());
    }

    public void callAdminLogin(View view) {

        Intent intent = new Intent(getApplicationContext(), AdminLogin.class);

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.Admin_login), "transition_login");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SwitchWindow.this, pairs);
        startActivity(intent, options.toBundle());
    }
}