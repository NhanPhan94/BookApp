package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class EditProfile extends AppCompatActivity {
    EditText username,email, address, age, password, password2;
    Button save, cancel;
    Spinner interest;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        setTitle("Edit Profile");

        username = (EditText) findViewById(R.id.txtName);
        email = (EditText) findViewById(R.id.txtEmailRes);
        address = (EditText) findViewById(R.id.txtPostalCode);
        age = (EditText) findViewById(R.id.txtAge);
        interest = (Spinner) findViewById(R.id.spInterest);
        password = (EditText) findViewById(R.id.txtPassword);

        loadData();

    }
    private void loadData(){

    }
}