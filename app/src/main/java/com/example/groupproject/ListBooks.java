package com.example.groupproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListBooks extends AppCompatActivity {
    DBHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_books);

        ListView listView = (ListView) findViewById(R.id.listView);
        Bundle bundle = getIntent().getExtras();
        //To handle a case when no data is passed
        if(bundle!= null) {
            String postaldata = bundle.getString("data");
            myDB = new DBHelper(this);

            ArrayList<String> bookList = new ArrayList<>();
            ArrayList<String> ownerList = new ArrayList<>();
            Cursor data = myDB.viewNearbyBooks(postaldata);

            if (data.getCount() > 0) {
                while (data.moveToNext()) {
                    bookList.add(data.getString(0));
                    //ownerList.add(data.getString(1));

                    ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookList);
                    listView.setAdapter(listAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            int itemPosition = i;
                            String value = (String) listView.getItemAtPosition(itemPosition);
                            //Intent ownerContactScreen = new Intent(ListBooks.this, OwnerContact.class);
                            Intent ownerContactScreen = new Intent(getApplicationContext(), OwnerContact.class);
                            ownerContactScreen.putExtra("bookTitleData", value);
                            ListBooks.this.startActivity(ownerContactScreen);
                        }
                    });
                    //str.append(" BookTitle : " + c.getString(0));
                    //str.append(" OwnerContact : " + c.getString(1));
                    //str.append(" Id : " + c.getString(2));
                        /*str.append(" cell : " + c.getString(3));
                        str.append(" provname: " + c.getString(3));*/
                    //str.append("\n");
                }
                //txtOutput.setText(str);
                //"Please click on the desired book to contact the Owner"
                // String[] postalCodeArray ={"V1M 3B5", "V2Y 1A5", "V3A 5G2", "V3R0A4", "V3M 5Z5"};
            } else {
                Toast.makeText(ListBooks.this,
                        "Sorry, no neraby books available at this address", Toast.LENGTH_SHORT).show();
            }

        }
    }
}