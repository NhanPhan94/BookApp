package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

// Done by Steven Ning-300324107

public class Register extends AppCompatActivity {

    EditText username,email, address, age, password, password2;
    Button register;
    Spinner interest;
    DBHelper DB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.txtName);
        email = (EditText) findViewById(R.id.txtEmailRes);
        address = (EditText) findViewById(R.id.txtAddress);
        age = (EditText) findViewById(R.id.txtAge);
        interest = (Spinner) findViewById(R.id.spInterest);
        password = (EditText) findViewById(R.id.txtPassword);
        password2 = (EditText) findViewById(R.id.txtConfirmPass);
        register = (Button) findViewById(R.id.btnRegister);
        DB = new DBHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String email_user = email.getText().toString();
                String addr_user = address.getText().toString();
                String age_user = age.getText().toString();
                String inte_user = interest.getSelectedItem().toString();
                String pass = password.getText().toString();
                String pass2 = password2.getText().toString();



                if(user.equals("") || pass.equals("") || pass2.equals("")){
                    // if username,password or password confirmation is blank tells user to fill out all required fields

                    Toast.makeText(Register.this,"Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else{

                    if(pass.equals(pass2)){
                        // checking if password is equal to conformation password

                        Boolean checkuser = DB.checkusername(user);

                        if(checkuser==false){

                            Boolean insert = DB.insertData(user,email_user,addr_user,age_user,inte_user, pass);

                            if(insert==true){

                                Toast.makeText(Register.this, "Registered", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Login.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(Register.this, "User already exists", Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Toast.makeText(Register.this,"Passwords do not match", Toast.LENGTH_SHORT).show();
                        }
                    }

                }

            }
        });



    }


}