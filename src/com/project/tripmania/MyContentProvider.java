package com.project.tripmania;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {

	private DB myDB;
	
	private static final String AUTHORITY = "com.project.tripmania.MyContentProvider";
	private static final String TABLE_SAVED_LOCATIONS = "savedLocations";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE_SAVED_LOCATIONS);
	
	public static final int SAVEDLOCATION = 1;
	
	private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

	static {
		sURIMatcher.addURI(AUTHORITY, TABLE_SAVED_LOCATIONS, SAVEDLOCATION);
	}
	
	
	@Override
	public boolean onCreate() {
		myDB = new DB(getContext());
		return false;
	}
	
	
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
		        String[] selectionArgs, String sortOrder) {

		Cursor cursor;
		int uriType = sURIMatcher.match(uri);

		switch (uriType) {
		    case SAVEDLOCATION:
		    	cursor = myDB.getAllSavedLocations();
		   		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		        break;
		    default:
		        throw new IllegalArgumentException("Unknown URI");
		}
		return cursor;
	} 
	
	
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Uri insert(Uri arg0, ContentValues arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return 0;
	}
}
