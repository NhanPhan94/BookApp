package com.example.groupproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;



public class DBHelper extends SQLiteOpenHelper{

    public static final String DBNAME = "BookSharing.db";
    final static int DATABASE_VERSION = 1;
    final static String TABLE1_NAME = "Users_Information";
    final static String T1COL1 = "Id";
    final static String T1COL2 = "Username";
    final static String T1COL3 = "Email";
    final static String T1COL4 = "Address";
    final static String T1COL5 = "Age";
    final static String T1COL6 = "Interest";
    final static String T1COL7 = "Password";


    public DBHelper( Context context)  {

        super(context, DBNAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        String query = "CREATE TABLE " + TABLE1_NAME + "(" + T1COL1 + " Integer PRIMARY KEY,"+
                T1COL2 + " Text, " + T1COL3 + " Text, " + T1COL4 + " Text, " +
                T1COL5 + " Text, " + T1COL6 + " Text, " + T1COL7 + " Text)";

        MyDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int oldVersion, int newVersion) {
        MyDatabase.execSQL("DROP TABLE " + TABLE1_NAME);
        onCreate(MyDatabase);
    }

    public Boolean insertData(String username,String email, String address, String age,String interest, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T1COL2,username);
        contentValues.put(T1COL3,email);
        contentValues.put(T1COL4,address);
        contentValues.put(T1COL5,age);
        contentValues.put(T1COL6,interest);
        contentValues.put(T1COL7,password);
        long result = MyDatabase.insert(TABLE1_NAME,null,contentValues);

        if(result==-1){
            return false;
        }
        else{
            return true;
        }

    }

    public Boolean checkusername(String username){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE1_NAME + " WHERE username = ?" ;
        Cursor cursor = MyDatabase.rawQuery(query, new String[] {username});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE1_NAME + " WHERE username = ? AND password = ?";
        Cursor cursor = MyDatabase.rawQuery(query, new String[] {username,password});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }


}