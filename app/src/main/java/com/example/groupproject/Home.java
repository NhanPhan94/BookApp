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
        private Button btnEditProfile, btnViewProfile, btnlogout;
        private UserProfile userprofile;
        private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle(R.string.welcome);

        welcomeText = findViewById(R.id.txtWelcome);

        Intent intent = getIntent();
        userprofile = (UserProfile) intent.getSerializableExtra("userprofile");
        welcomeText.setText("Welcome " + userprofile.getUsername());

        Button addBook = (Button)findViewById(R.id.btnAddUpdate);
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, AddingBook.class));
            }
        });

        btnEditProfile = findViewById(R.id.btnEditProfile);
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btnEditIntent = new Intent(Home.this, EditProfile.class);

                btnEditIntent.putExtra("userprofile",userprofile);

                startActivity(btnEditIntent);
            }
        });

//        btnLogOut = findViewById(R.id.btnLogout);
//
//        btnLogOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(Home.this, Login.class));
//            }
//        });

        session = new Session(this);
        if (!session.loggedin()) {
            logout();
        }
        btnlogout = (Button) findViewById(R.id.btnLogout);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        btnViewProfile = findViewById(R.id.btnViewProfile);

        btnViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btnViewIntent = new Intent(Home.this, ViewProfile.class);
                btnViewIntent.putExtra("userprofile",userprofile);
                startActivity(btnViewIntent);

            }
        });
    }
    private void logout(){
        session.setLoggedin(false);
        startActivity(new Intent(Home.this,Login.class));
    }
}