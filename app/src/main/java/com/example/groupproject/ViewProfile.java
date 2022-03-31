package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import entities.UserProfile;

public class ViewProfile extends AppCompatActivity {
    EditText username,email, address, age, password;
    Button btnUpdate, btnCancel;
    Spinner interest;
    UserProfile userprofile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        setTitle("View your Profile");

        username = (EditText) findViewById(R.id.txtName);
        email = (EditText) findViewById(R.id.txtEmailRes);
        address = (EditText) findViewById(R.id.txtPostalCode);
        age = (EditText) findViewById(R.id.txtAge);
        interest = (Spinner) findViewById(R.id.spInterest);
        password = (EditText) findViewById(R.id.txtPassword);

        loadData();

        btnCancel = findViewById(R.id.btnCancelChange);
        btnUpdate = findViewById(R.id.btnSave);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewProfile.this, Home.class);
                intent.putExtra("userprofile", userprofile);
                startActivity(intent);

            }
        });
    }
    private void loadData(){
        Intent intent = getIntent();
        userprofile = (UserProfile) intent.getSerializableExtra("userprofile");
        username.setText(userprofile.getUsername());
        email.setText(userprofile.getEmail());
        address.setText(userprofile.getAddress());
        age.setText(userprofile.getAge());
        password.setText(userprofile.getPassword());
    }
}