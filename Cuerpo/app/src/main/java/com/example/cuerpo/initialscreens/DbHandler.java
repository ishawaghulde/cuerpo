package com.example.cuerpo.initialscreens;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "infodb";
    private static final String TABLE_Users = "details";
    private static final String KEY_ID = "id";
    private static final String KEY_DATE = "date";
    private static final String KEY_WATER_DRANK = "water_drank";
    private static final String KEY_SLEPT_HOURS = "slept_hours";
    private static final String KEY_CALORIES_CONSUMED = "calories_consumed";
    private static final String KEY_TOTAL_CALORIES = "total_calories";
    private static final String KEY_TOTAL_STEPS = "total_steps";

    public DbHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Users + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_DATE + " TEXT,"
                + KEY_WATER_DRANK + " INTEGER," + KEY_SLEPT_HOURS + " TEXT," + KEY_CALORIES_CONSUMED + " INTEGER," + KEY_TOTAL_CALORIES + " INTEGER," + KEY_TOTAL_STEPS + " INTEGER"
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
        // Create tables again
        onCreate(db);
    }

    void insertUserDetails(String date, int water_drank, String slept_hours, int calories_consumed, int total_calories, int total_steps) {
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_DATE, date);
        cValues.put(KEY_WATER_DRANK, water_drank);
        cValues.put(KEY_SLEPT_HOURS, slept_hours);
        cValues.put(KEY_CALORIES_CONSUMED, calories_consumed);
        cValues.put(KEY_TOTAL_CALORIES, total_calories);
        cValues.put(KEY_TOTAL_STEPS, total_steps);
        db.close();
    }


    public boolean GetUserByDate(String todayDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_Users, new String[]{KEY_WATER_DRANK, KEY_SLEPT_HOURS, KEY_CALORIES_CONSUMED, KEY_TOTAL_CALORIES, KEY_TOTAL_STEPS}, KEY_DATE + "=?", new String[]{todayDate}, null, null, null, null);
        if (cursor.moveToNext()) {
            return true;
        }
        cursor.close();
        return false;
    }
    public int GetStepsByDate(String date){
        SQLiteDatabase db = this.getWritableDatabase();
        int steps = 0;
        String query = "SELECT total_steps FROM " +TABLE_Users;
        Cursor cursor = db.rawQuery(query,null);
        while(cursor.moveToNext()) {
            steps = cursor.getInt(cursor.getColumnIndex(KEY_TOTAL_STEPS));
        }
        return steps;
    }



    public String GetLastRow() {
        SQLiteDatabase db = this.getWritableDatabase();
        String userList = "";
        String query = "SELECT  * FROM " + TABLE_Users + " ORDER BY id DESC";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {

            userList = cursor.getString(cursor.getColumnIndex(KEY_DATE));
        }
        cursor.close();
        return userList;
    }

    public void updateDetails(String date, int water_drank, String slept_hours, int calories_consumed, int total_calories, int total_steps){
        SQLiteDatabase db = this.getWritableDatabase();
//        "UPDATE" + TABLE_Users
//        "SET" + KEY_WATER_DRANK +" = " + water_drank, KEY_SLEPT_HOURS = slept_hours, KEY_CALORIES_CONSUMED = calories_consumed, KEY_TOTAL_CALORIES = total_calories, KEY_TOTAL_STEPS = total_steps;
//        WHERE KEY_DATE = date;

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_DATE,date);
        contentValues.put(KEY_WATER_DRANK,water_drank);
        contentValues.put(KEY_SLEPT_HOURS,slept_hours);
        contentValues.put(KEY_CALORIES_CONSUMED,calories_consumed);
        contentValues.put(KEY_TOTAL_CALORIES,total_calories);
        contentValues.put(KEY_TOTAL_STEPS,total_steps);
        db.update(TABLE_Users, contentValues, "date = ?",new String[] { date });

    }
}
