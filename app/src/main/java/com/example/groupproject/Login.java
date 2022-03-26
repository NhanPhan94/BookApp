package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// Done by Steven Ning-300324107

public class Login extends AppCompatActivity {

    EditText username,password;
    Button btnlogin;
    DBHelper DB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.txtEmailLog);
        password = (EditText) findViewById(R.id.txtPass);
        btnlogin = (Button) findViewById(R.id.btnLog);
        DB = new DBHelper(this);


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();


                if(user.equals("") || pass.equals("")){
                    //checks if any fields are blank in the password/username field, if empty asks for the prompt to be filled

                    Toast.makeText(Login.this,"Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if(checkuserpass==true){
                        // checks if the username and password match in the database for the login, if correct, logs the user in and sends to the home activity/page

                        Toast.makeText(Login.this,"Sign in successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Home.class);
                        startActivity(intent);
                    }else{
                        // if returns a false value, will tell the user the password/login do no match
                        Toast.makeText(Login.this,"Invalid info", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });



    }



}