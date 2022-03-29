package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import entities.UserProfile;

public class Home extends AppCompatActivity {
//    private TextView welcomeText;
//    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        setTitle(R.string.welcome);
//        DB = new DBHelper(this);
//        welcomeText = findViewById(R.id.txtWelcome);
//
//        Intent intent = getIntent();
//        UserProfile profile = (UserProfile) intent.getSerializableExtra("profile");
//      //  UserProfile userProfile = (UserProfile) intent.getSerializableExtra("users_information");
//        welcomeText.setText(getString(R.string.welcome) + " " + profile.getUsername());

        Button addBook = (Button)findViewById(R.id.btnAddUpdate);
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, AddingBook.class));
            }
        });

    }
}