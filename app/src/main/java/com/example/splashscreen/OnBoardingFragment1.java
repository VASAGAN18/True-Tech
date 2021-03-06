package com.example.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.splashscreen.common.LoginSignup.SwitchWindow;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class OnBoardingFragment1 extends Fragment {

    FloatingActionButton fab;


    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_on_boarding_fragment1, container, false);

        fab = root.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SwitchWindow.class);
                startActivity(intent);
            }
        });

        return root;
    }
}
