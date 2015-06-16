package com.project.tripmania;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.drive.Drive;

public class Major_Menu_Options extends Activity implements ConnectionCallbacks, OnConnectionFailedListener {
	double lat;
	double lng;	String location ;
	GoogleApiClient mGoogleApiClient;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_major__menu__options);
		
		Intent i = getIntent();
		String City = i.getStringExtra("City");
		location = City;
		lat = i.getDoubleExtra("foundLatitude", 0);
		lng = i.getDoubleExtra("foundLongitude", 0);
		
		mGoogleApiClient = new GoogleApiClient.Builder(this)
			        .addApi(Drive.API)
			        .addScope(Drive.SCOPE_FILE)
			        .addConnectionCallbacks( this)
			        .addOnConnectionFailedListener( this)
			        .build();

		
		setTitle(City);
		
	}
	 public void onConnected(Bundle connectionHint) {
	        // Connected to Google Play services!
	        // The good stuff goes here.
	    }

	    @Override
	    public void onConnectionSuspended(int cause) {
	        // The connection has been interrupted.
	        // Disable any UI components that depend on Google APIs
	        // until onConnected() is called.
	    }

	    @Override
	    public void onConnectionFailed(ConnectionResult result) {
	        // This callback is important for handling errors that
	        // may occur while attempting to connect with Google.
	        //
	        // More about this in the 'Handle Connection Failures' section.
	        
	    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.major__menu__options, menu);
		return true;
	}
	
	public void checkInfo(View v)
    {
    	int id = v.getId();
    	if(id == R.id.map_id)
    	{
			Intent intent = new Intent(this, MapsActivity.class);
			intent.putExtra("lat", lat);
			intent.putExtra("lng", lng);
			startActivity(intent);
    	}
    	
    	else if(id == R.id.info_id)
    	{
			Intent intent = new Intent(this, AboutActivity.class);
			startActivity(intent);
    	}
    	
    	if(id == R.id.rest_id)
    	{
			Intent intent = new Intent(this, SearchPlacesActivity.class);
			intent.putExtra("title","Food Valleys");
			intent.putExtra("types","restaurants|food|bar|bakery");
			intent.putExtra("foundLatitude",lat);
			intent.putExtra("foundLongitude",lng);
			startActivity(intent);
    	}
    	
    	else if(id == R.id.nearbyBaloonAnimator)
    	{
    		Intent intent = new Intent(this, SearchPlacesActivity.class);
    		intent.putExtra("title","Nearby Places");
    		intent.putExtra("types","places|establishment");
    		intent.putExtra("foundLatitude",lat);
			intent.putExtra("foundLongitude",lng);
			startActivity(intent);
    	}
    	
    	
    
    }
	
	@Override
	  public void onStart() {
	    super.onStart();
	    // The rest of your onStart() code.
	    //EasyTracker.getInstance(this).activityStart(this);  // Add this method.
	  }

	  @Override
	  public void onStop() {
	    super.onStop();
	    // The rest of your onStop() code.
	    mGoogleApiClient.connect();
	    //EasyTracker.getInstance(this).activityStop(this);  // Add this method.
	  }
}
