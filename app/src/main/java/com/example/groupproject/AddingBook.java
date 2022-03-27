package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class AddingBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_book);

        EditText txtBookTitle = (EditText) findViewById(R.id.txtBookTitle);
        EditText txtAuthorName = (EditText) findViewById(R.id.txtAuthor);
        EditText txtPubisherName = (EditText) findViewById(R.id.txtPublisherName);
        EditText txtPublicationYear =(EditText) findViewById(R.id.txtPublicationYear);
        CheckBox chkShare = (CheckBox) findViewById(R.id.chkShare);
        CheckBox chkRent = (CheckBox) findViewById(R.id.chkRent);
        CheckBox chkGiveAway = (CheckBox) findViewById(R.id.chkGiveaway);
        Button btnAddBook = (Button) findViewById(R.id.btnAddUpdate);
        Button btnCancel = (Button) findViewById(R.id.btnCancelAdd);

        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        int userid = sharedPreferences.getInt("key1",0);

        DBHelper  dbHelper= new DBHelper(this);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddingBook.this, Home.class));
            }
        });

        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bookTitle = txtBookTitle.getText().toString();
                String author = txtAuthorName.getText().toString();
                String publisher = txtPubisherName.getText().toString();
                String publicationYear = txtPublicationYear.getText().toString();
                String borrowActivity = "";

                if(chkShare.isChecked())
                {
                    borrowActivity = "Share";
                }
                else if(chkRent.isChecked())
                {
                    borrowActivity = "Rent";
                }
                else if(chkGiveAway.isChecked())
                {
                    borrowActivity = "GiveAway";
                }

                Boolean insert = dbHelper.insertBookData(bookTitle,author,publisher,publicationYear,borrowActivity,1);
                if(insert){
                    Toast.makeText(AddingBook.this,"Book is Successfully Added", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddingBook.this, Home.class));
                }
                else{

                    Toast.makeText(AddingBook.this,"Book is not added. Please try again.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddingBook.this, Home.class));
                }


            }
        });
    }
}