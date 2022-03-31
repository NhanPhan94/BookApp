package com.example.groupproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import entities.UserProfile;


public class DBHelper extends SQLiteOpenHelper{

    public static final String DBNAME = "BookSharing.db";
    final static int DATABASE_VERSION = 1;
    final static String TABLE1_NAME = "Users_Information";
    final static String TABLE2_NAME = "Book_Information";
    final static String T1COL1 = "Id";
    final static String T1COL2 = "Username";
    final static String T1COL3 = "Email";
    final static String T1COL4 = "Address";
    final static String T1COL5 = "Age";
    final static String T1COL6 = "Interest";
    final static String T1COL7 = "Password";
    final static String T2COL1 = "BookID";
    final static String T2COL2 = "BookTitle";
    final static String T2COL3 = "AuthorName";
    final static String T2COL4 = "PublisherName";
    final static String T2COL5 = "PublicationYear";
    final static String T2COL6 = "BorrowActivity";





    public DBHelper( Context context)  {

        super(context, DBNAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        String query1 = "CREATE TABLE " + TABLE1_NAME + "(" + T1COL1 + " Integer PRIMARY KEY,"+
                T1COL2 + " Text, " + T1COL3 + " Text, " + T1COL4 + " Text, " +
                T1COL5 + " Text, " + T1COL6 + " Text, " + T1COL7 + " Text);";



//        String query2 ="CREATE TABLE " +
//                TABLE2_NAME +
//                "(" + T2COL1 + " Integer PRIMARY KEY,"+
//                T2COL2 + " Text, " +
//                T2COL3 + " Text, " +
//                T2COL4 + " Text, " +
//                T2COL5 + " Integer, " +
//                "FOREIGN KEY(" + T1COL1 + ") REFERENCES " +TABLE1_NAME+"("+T1COL1 + ")"+
//                ");";

            MyDatabase.execSQL(query1);
            MyDatabase.execSQL("create Table Book_Information(bookID INT primary key AUTOINCREMENT, bookTitle TEXT, authorName TEXT, " +
                    "publisherName TEXT, publicationYear TEXT, borrowActivity TEXT, Id INT," +
                    " foreign key(Id) REFERENCES Users_Information(Id))");



    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int oldVersion, int newVersion) {
        MyDatabase.execSQL("DROP TABLE " + TABLE1_NAME);
        MyDatabase.execSQL("DROP TABLE " + TABLE2_NAME);
        onCreate(MyDatabase);
    }

    public Boolean insertData(UserProfile userProfile) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T1COL2,userProfile.getUsername());
        contentValues.put(T1COL3,userProfile.getEmail());
        contentValues.put(T1COL4,userProfile.getAddress());
        contentValues.put(T1COL5,userProfile.getAge());
        contentValues.put(T1COL6,userProfile.getInterest());
        contentValues.put(T1COL7,userProfile.getPassword());
        long result = MyDatabase.insert(TABLE1_NAME,null,contentValues);

        if(result==-1){
            return false;
        }
        else{
            return true;
        }

    }

    public Boolean insertBookData(String bookTitle, String authorName, String publisherName, String publicationYear,
                                  String borrowActivity, int userId){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("bookTitle",bookTitle);
        contentValues.put("authorName",authorName);
        contentValues.put("publisherName",publisherName);
        contentValues.put("publicationYear",publicationYear);
        contentValues.put("borrowActivity",borrowActivity);
        contentValues.put("Id",userId);

        long result = MyDatabase.insert(TABLE2_NAME,null,contentValues);

        if(result==-1){
            return false;
        }
        else{

            return true;
        }
    }
    public UserProfile login (String username, String password){
        UserProfile userProfile = null;
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT * FROM " + TABLE1_NAME + " WHERE username = ? AND password = ?";
            Cursor cursor = db.rawQuery(query, new String[]{username, password});
            if(cursor.moveToFirst()){
                userProfile = new UserProfile();
                userProfile.setUserId(cursor.getInt(0));
                userProfile.setUsername(cursor.getString(1));
                userProfile.setEmail(cursor.getString(2));
                userProfile.setAddress(cursor.getString(3));
                userProfile.setAge(cursor.getString(4));
                userProfile.setInterest(cursor.getString(5));
                userProfile.setPassword(cursor.getString(6));
            }
        }
        catch (Exception e){
            userProfile = null;
        }
        return userProfile;
    }

    public UserProfile checkuser (String username){
        UserProfile userProfile = null;
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT * FROM " + TABLE1_NAME + " WHERE username = ?";
            Cursor cursor = db.rawQuery(query, new String[]{username});
            if(cursor.moveToFirst()){
                userProfile = new UserProfile();
                userProfile.setUserId(cursor.getInt(0));
                userProfile.setUsername(cursor.getString(1));
                userProfile.setEmail(cursor.getString(2));
                userProfile.setAddress(cursor.getString(3));
                userProfile.setAge(cursor.getString(4));
                userProfile.setInterest(cursor.getString(5));
                userProfile.setPassword(cursor.getString(6));
            }
        }
        catch (Exception e){
            userProfile = null;
        }
        return userProfile;
    }

    public UserProfile find (int id){
        UserProfile userProfile = null;
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT * FROM " + TABLE1_NAME + " WHERE id = ?";
            Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});
            if(cursor.moveToFirst()){
                userProfile = new UserProfile();
                userProfile.setUserId(cursor.getInt(0));
                userProfile.setUsername(cursor.getString(1));
                userProfile.setEmail(cursor.getString(2));
                userProfile.setAddress(cursor.getString(3));
                userProfile.setAge(cursor.getString(4));
                userProfile.setInterest(cursor.getString(5));
                userProfile.setPassword(cursor.getString(6));
            }
        }
        catch (Exception e){
            userProfile = null;
        }
        return userProfile;
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

    public Boolean updateData(UserProfile userProfile) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T1COL2,userProfile.getUsername());
        contentValues.put(T1COL3,userProfile.getEmail());
        contentValues.put(T1COL4,userProfile.getAddress());
        contentValues.put(T1COL5,userProfile.getAge());
        contentValues.put(T1COL6,userProfile.getInterest());
        contentValues.put(T1COL7,userProfile.getPassword());
        long result = MyDatabase.update(TABLE1_NAME, contentValues, T1COL1 + " = ?",
                new String[] {String.valueOf(userProfile.getUserId())});

        if(result < 0){
            return false;
        }
        else{
            return true;
        }

    }


}