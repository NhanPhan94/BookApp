package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import entities.UserProfile;

public class Home extends AppCompatActivity {
        private TextView welcomeText;
        private Spinner spProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle(R.string.welcome);

        welcomeText = findViewById(R.id.txtWelcome);

        Intent intent = getIntent();
        UserProfile userprofile = (UserProfile) intent.getSerializableExtra("userprofile");
        welcomeText.setText("Welcome " + userprofile.getUsername());

//        spProfile = findViewById(R.id.spProfile);
//        spProfile.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if(i==0){
//                    startActivity(new Intent(Home.this, ViewProfile.class));
//                }
//                if(i==1){
//                    startActivity(new Intent(Home.this,EditProfile.class));
//                }
//                else {
//                    startActivity(new Intent(Home.this, Register.class));
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });




        Button addBook = (Button)findViewById(R.id.btnAddUpdate);
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, AddingBook.class));
            }
        });

    }
}