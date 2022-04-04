package com.example.groupproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class BorrowBook extends AppCompatActivity {
    //DBHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_book);

        //databaseHelper = new DBHelper(this);
        EditText postalCode = findViewById(R.id.inputPostalCode);
        Button btnFind = (Button) findViewById(R.id.btnFindNearByBooks);
        //TextView txtOutput = findViewById(R.id.txtBookDb);
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(BorrowBook.this, ListBooks.class);
                Intent intent = new Intent(getApplicationContext(), ListBooks.class);
                intent.putExtra("data", postalCode.getText().toString());
                startActivity(intent);
                //startActivity(new Intent(BorrowBook.this, NearByBooks.class));
                //Cursor c = databaseHelper.viewBookOwner(postalCode.getText().toString());
                //StringBuilder str = new StringBuilder();
            }
        });
    }
}