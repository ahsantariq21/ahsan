package com.project.tripmania;
 
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.place.locator.AlertDialogManager;
import com.google.place.locator.ConnectionDetector;
import com.google.place.locator.GooglePlaces;
import com.google.place.locator.PlaceDetails;
 
//import com.bugsense.trace.BugSenseHandler;

public class SinglePlaceActivity extends Activity implements OnMapLongClickListener {
	
	String messageBody;
	double lat, lng;
	GoogleMap googleMap;
	LatLng LL;
	String loc;
	
	// flag for Internet connection status
    Boolean isInternetPresent = false;
 
    // Connection detector class
    ConnectionDetector cd;
     
    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();
 
    // Google Places
    GooglePlaces googlePlaces;
     
    // Place Details
    PlaceDetails placeDetails;
     
    // Progress dialog
    ProgressDialog pDialog;
     
    // KEY Strings
    public static String KEY_REFERENCE = "reference"; // id of the place
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
      //  BugSenseHandler.initAndStartSession(this, "630104c8");
        setContentView(R.layout.single_place);
         
        Intent i = getIntent();
        String title = i.getStringExtra("title");
        loc = title;
        messageBody = "Hey!!\nLets make a plan to visit " + title;
        setTitle(title);
        // Place referece id
        String reference = i.getStringExtra(KEY_REFERENCE);
         
        // Calling a Async Background thread
        new LoadSinglePlaceDetails().execute(reference);
    }
     
     
    /**
     * Background Async Task to Load Google places
     * */
    class LoadSinglePlaceDetails extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(SinglePlaceActivity.this);
            pDialog.setMessage("Loading profile ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
 
        /**
         * getting Profile JSON
         * */
        protected String doInBackground(String... args) {
            String reference = args[0];
             
            // creating Places class object
            googlePlaces = new GooglePlaces();
 
            // Check if used is connected to Internet
            try {
                placeDetails = googlePlaces.getPlaceDetails(reference);
 
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
 
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed Places into LISTVIEW
                     * */
                    if(placeDetails != null){
                        String status = placeDetails.status;
                        Animation animator = AnimationUtils.loadAnimation(SinglePlaceActivity.this, R.anim.left_to_right_in);
                        // check place deatils status
                        // Check for all possible status
                        if(status.equals("OK")){
                            if (placeDetails.result != null) {
                                String name = placeDetails.result.name;
                                String icon = placeDetails.result.icon;
                                String address = placeDetails.result.formatted_address;
                                String phone = placeDetails.result.formatted_phone_number;
                                
                                String latitude = Double.toString(placeDetails.result.geometry.location.lat);
                                String longitude = Double.toString(placeDetails.result.geometry.location.lng);
                                 
                                Log.d("Place ", name + address + phone + latitude + longitude);
                                lat = placeDetails.result.geometry.location.lat;
                                lng = placeDetails.result.geometry.location.lng;
                                LL = new LatLng(lat, lng);
                                GetMap();
                                
                                // Displaying all the details in the view
                                // single_place.xml
                                
                                TextView lbl_name = (TextView) findViewById(R.id.name);
                                TextView lbl_address = (TextView) findViewById(R.id.address);
                                TextView lbl_phone = (TextView) findViewById(R.id.phone);
                                TextView lbl_name_head = (TextView) findViewById(R.id.AppsName_id);
                                TextView lbl_phone_head = (TextView) findViewById(R.id.complete_number_heading);
                                TextView lbl_address_head = (TextView) findViewById(R.id.add);
                                
                                 
                                // Check for null data from google
                                // Sometimes place details might missing
                                name = name == null ? "Not present" : name; // if name is null display as "Not present"
                                address = address == null ? "Not present" : address;
                                phone = phone == null ? "Not present" : phone;
                                latitude = latitude == null ? "Not present" : latitude;
                                longitude = longitude == null ? "Not present" : longitude;
                                 
                                lbl_name.setText(name);
                                lbl_address.setText(address);
                                lbl_phone.setText(phone);
                                animator.setDuration(700);
                                lbl_name.startAnimation(animator);
                                animator.setDuration(700);
                                lbl_address.startAnimation(animator);
                                animator.setDuration(700);
                                lbl_phone.startAnimation(animator);
                                animator.setDuration(700);
                                lbl_name_head.startAnimation(animator);
                                animator.setDuration(700);
                                lbl_phone_head.startAnimation(animator);
                                animator.setDuration(700);
                                lbl_address_head.startAnimation(animator);
                            }
                        }
                        else if(status.equals("ZERO_RESULTS")){
                            alert.showAlertDialog(SinglePlaceActivity.this, "Near Places",
                                    "Sorry no place found.",
                                    false);
                        }
                        else if(status.equals("UNKNOWN_ERROR"))
                        {
                            alert.showAlertDialog(SinglePlaceActivity.this, "Places Error",
                                    "Sorry unknown error occured.",
                                    false);
                        }
                        else if(status.equals("OVER_QUERY_LIMIT"))
                        {
                            alert.showAlertDialog(SinglePlaceActivity.this, "Places Error",
                                    "Sorry query limit to google places is reached",
                                    false);
                        }
                        else if(status.equals("REQUEST_DENIED"))
                        {
                            alert.showAlertDialog(SinglePlaceActivity.this, "Places Error",
                                    "Sorry error occured. Request is denied",
                                    false);
                        }
                        else if(status.equals("INVALID_REQUEST"))
                        {
                            alert.showAlertDialog(SinglePlaceActivity.this, "Places Error",
                                    "Sorry error occured. Invalid Request",
                                    false);
                        }
                        else
                        {
                            alert.showAlertDialog(SinglePlaceActivity.this, "Places Error",
                                    "Sorry error occured.",
                                    false);
                        }
                    }else{
                        alert.showAlertDialog(SinglePlaceActivity.this, "Places Error",
                                "Sorry error occured.",
                                false);
                    }
                     
                     
                }
            });
 
        }
 
    }
    
    public void sendSMS(View v)
	{
		try 
		{
            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
            smsIntent.putExtra("sms_body", messageBody);
            smsIntent.setType("vnd.android-dir/mms-sms");
            startActivity(smsIntent);
        } catch (Exception e) 
        {
            Toast.makeText(getApplicationContext(), "SMS faild!",
            Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
	}
	
	public void sendEMAIL(View v)
	{
		  Log.i("Send email", "");
	      String[] TO = {""};
	      String[] CC = {""};
	      Intent emailIntent = new Intent(Intent.ACTION_SEND);
	      emailIntent.setData(Uri.parse("mailto:"));
	      emailIntent.setType("text/plain");


	      emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
	      emailIntent.putExtra(Intent.EXTRA_CC, CC);
	      emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Trip To a Place");
	      emailIntent.putExtra(Intent.EXTRA_TEXT, messageBody);

	      try {
	         startActivity(Intent.createChooser(emailIntent, "Send mail..."));
	         finish();
	         Log.i("Finished sending email...", "");
	      } catch (android.content.ActivityNotFoundException ex) {
	    	 // BugSenseHandler.sendException(ex);
	         Toast.makeText(SinglePlaceActivity.this,"There is no email client installed.", Toast.LENGTH_SHORT).show();
      }
	}
    
	  @Override
	  public void onStart() {
	    super.onStart();
	    // The rest of your onStart() code.
	    EasyTracker.getInstance(this).activityStart(this);  // Add this method.
	  }
	
	  @Override
	  public void onStop() {
	    super.onStop();
	    // The rest of your onStop() code.
	    EasyTracker.getInstance(this).activityStop(this);  // Add this method.
	  }
	  
	  public void GetMap(){
		 
		  try {
				// Loading map
				initilizeMap();

				// Changing map type
				googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
				// googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
				// googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
				// googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
				// googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);

				// Showing / hiding your current location
				googleMap.setMyLocationEnabled(true);

				// Enable / Disable zooming controls
				googleMap.getUiSettings().setZoomControlsEnabled(false);

				// Enable / Disable my location button
				googleMap.getUiSettings().setMyLocationButtonEnabled(true);

				// Enable / Disable Compass icon
				googleMap.getUiSettings().setCompassEnabled(true);

				// Enable / Disable Rotate gesture
				googleMap.getUiSettings().setRotateGesturesEnabled(true);

				// Enable / Disable zooming functionality
				googleMap.getUiSettings().setZoomGesturesEnabled(true);
				
				googleMap.setOnMapLongClickListener(this);
				
				CameraPosition cameraPosition = new CameraPosition.Builder()
						.target(LL).zoom(15).build();
				
				Marker mark = googleMap.addMarker(new MarkerOptions()
			            .title(loc)
			            .snippet("AAA-BB, XYZ Town, ADS")
			            .position(LL)
			            .draggable(true)
			            .icon(BitmapDescriptorFactory
			            		.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
				
				mark.showInfoWindow();
				
				googleMap.animateCamera(CameraUpdateFactory
						.newCameraPosition(cameraPosition));

			} catch (Exception e) {
				//BugSenseHandler.sendException(e);
				e.printStackTrace();
			}
	  }
	  
	@Override
	protected void onResume() {
		super.onResume();
		initilizeMap();
	}

	/**
	 * function to load map If map is not created it will create it for you
	 * */
	private void initilizeMap() {
		if (googleMap == null) {
			MapFragment mf = (MapFragment) getFragmentManager()
						.findFragmentById(R.id.map_single);
			
			googleMap = mf.getMap();
			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}
	
	@Override
    public void onMapLongClick(LatLng point) {

		Intent intent = new Intent(this, MapsActivity.class);
		intent.putExtra("lat", lat);
		intent.putExtra("lng", lng);
		startActivity(intent);

    }
}