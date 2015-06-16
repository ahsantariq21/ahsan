package com.project.tripmania;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper{

	  // All Static variables
  // Database Version
  private static final int DATABASE_VERSION = 2;

  // Database Name
  private static final String DATABASE_NAME = "Tripmania_DB";

  // Contacts table name
  private static final String TABLE_SAVED_LOCATIONS = "savedLocations";
  private static final String TABLE_SAVED_POSTS = "posts";
  
  private static int counter = 1;
  
  // Contacts Table Columns names
  private static final String KEY_ID = "id";
  private static final String KEY_LOCATION_NAME = "location_name";
  private static final String KEY_LAT = "latitude";
  private static final String KEY_LONG = "longitude";
  
  private static final String KEY_POST_ID = "post_id";
  
  public DB(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	
  }

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		  String CREATE_SAVEDLOCATION_TABLE = "CREATE TABLE " + TABLE_SAVED_LOCATIONS + "("
	                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_LOCATION_NAME + " TEXT,"
	                + KEY_LAT + " DOUBLE," + KEY_LONG + " DOUBLE)";
	        db.execSQL(CREATE_SAVEDLOCATION_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
		// Drop older table if existed
      db.execSQL("DROP TABLE IF EXISTS " + TABLE_SAVED_LOCATIONS);

      String CREATE_SAVEDLOCATION_TABLE = "CREATE TABLE " + TABLE_SAVED_LOCATIONS + "("
              + KEY_ID + " INTEGER PRIMARY KEY," + KEY_LOCATION_NAME + " TEXT,"
              + KEY_LAT + " DOUBLE," + KEY_LONG + " DOUBLE)";
      db.execSQL(CREATE_SAVEDLOCATION_TABLE);
      
      String CREATE_POST_TABLE = "CREATE TABLE " + TABLE_SAVED_POSTS + "("
              + KEY_POST_ID + " INTEGER PRIMARY KEY )";
      db.execSQL(CREATE_POST_TABLE);
      
      // Create tables again
      //onCreate(db);
	}
	
	// Adding new data
    void adddata(MyData data) {
        SQLiteDatabase db = this.getWritableDatabase();
         ContentValues values = new ContentValues();
        
        values.put(KEY_ID, counter);
        values.put(KEY_LOCATION_NAME, data.getAddress()); // Marks
        values.put(KEY_LONG, data.getLongi()); // Star
        values.put(KEY_LAT, data.getLat()); // Course Name
        // Inserting Row
        db.insert(TABLE_SAVED_LOCATIONS, null, values);
        counter++;
        db.close(); // Closing database connection
    }
    
    public void add_post(String str)
    {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
	   values.put(KEY_POST_ID, str);
	   db.insert(TABLE_SAVED_POSTS, null, values);
	   db.close(); // Closing database connection
    }
	
	public Cursor getAllSavedLocations() {
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SAVED_LOCATIONS;
 
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor != null) {
            return cursor;
        }
        // return null
        return null;
    }
    
    public int getLastPostID()
    {
    	String selectQuery = "SELECT * FROM " + TABLE_SAVED_POSTS;
    	SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        
        String ans = new String();
        if(cursor.moveToLast())
        {
	        ans = cursor.getString(0);
        }
        return Integer.parseInt(ans);
    }
    
    public ArrayList<Integer> getAllPosts() {
        ArrayList<Integer> dataList = new ArrayList<Integer>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SAVED_POSTS;
 
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                // Adding data to list
                dataList.add(Integer.parseInt(cursor.getString(0)));
            } while (cursor.moveToNext());
        }
        // return data list
        return dataList;
    }
    
    	
    public ArrayList<MyData> getAlldata() {
        ArrayList<MyData> dataList = new ArrayList<MyData>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SAVED_LOCATIONS;
 
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MyData data = new MyData();
                data.setAddress(cursor.getString(1));
                data.setLogi(Double.parseDouble(cursor.getString(2)));
                data.setLat(Double.parseDouble(cursor.getString(3)));
                // Adding data to list
                dataList.add(data);
            } while (cursor.moveToNext());
        }
        // return data list
        return dataList;
    }
    
    public double getLatitude(int id)
    {
    	String selectQuery = "SELECT * FROM " + TABLE_SAVED_LOCATIONS + " WHERE "+KEY_ID +" = "+id;
    	SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        
        String ans = new String();
        if(cursor.moveToFirst())
        {
	        ans = cursor.getString(2);
        }
        return Double.parseDouble(ans);
    }
    
    public double getLongitude(int id)
    {
    	String selectQuery = "SELECT * FROM " + TABLE_SAVED_LOCATIONS + " WHERE "+KEY_ID +" = "+id;
    	SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        String ans = new String();
        if(cursor.moveToFirst())
        {
	        ans = cursor.getString(3);
        }
        return Double.parseDouble(ans);
    }
    
    public void deleteAll()
    {
    	SQLiteDatabase db = this.getWritableDatabase();
    	String delQuery = "DELETE FROM " + TABLE_SAVED_LOCATIONS;
    	db.execSQL(delQuery);
    	db.close();
    }
}