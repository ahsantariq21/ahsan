package com.project.tripmania;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import com.splunk.mint.Mint;



public class MainActivity extends Activity implements LocationListener{
	
	String location_str;
	GPSTracker gps;
	String add;
	DB databaseHandler;
	double lat, longi;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		Mint.initAndStartSession(MainActivity.this, "8c0c5070");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		databaseHandler = new DB(this);
		
		AdView adView = (AdView)this.findViewById(R.id.adView); 
		AdRequest adRequest = new AdRequest.Builder().build(); 
		adView.loadAd(adRequest);
		
		TextView txt = (TextView) findViewById(R.id.AppName_id);
		Typeface font = Typeface.createFromAsset(this.getAssets(), "anudi.ttf");  
		txt.setTypeface(font);
		
		Animation animator = AnimationUtils.loadAnimation(this, R.anim.left_to_right_in);
		
		Button b1 = (Button) findViewById(R.id.main_homeId);
		Button b2 = (Button) findViewById(R.id.main_currentId);
		Button b3 = (Button) findViewById(R.id.main_searchId);
		Button b4 = (Button) findViewById(R.id.main_aboutId);
		Button b5 = (Button) findViewById(R.id.main_changeLang);
		Button b6 = (Button) findViewById(R.id.main_Crash);
		Button b7 = (Button) findViewById(R.id.main_Parse_Json);
		TextView appName = (TextView) findViewById(R.id.AppName_id);
		
		
		animator.setDuration(1200);
		b1.startAnimation(animator);
		animator.setDuration(1200);
		b2.startAnimation(animator);
		animator.setDuration(1200);
		b3.startAnimation(animator);
		animator.setDuration(1200);
		b4.startAnimation(animator);
		animator.setDuration(1200);
		b5.startAnimation(animator);
		animator.setDuration(1200);
		b6.startAnimation(animator);
		animator.setDuration(1200);
		b7.startAnimation(animator);
		
		animator = AnimationUtils.loadAnimation(this, R.anim.fade_in);
		animator.setDuration(1000);
		appName.startAnimation(animator);
		
		
	
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
	
	public void about(View v)
	{
		Intent i = new Intent(this, AboutActivity.class);
		startActivity(i);
	}
	
	public void SavedLocation(View v)
	{
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Saved Locations");
        
        // Set an EditText view to get user input 
        final ListView LV = new ListView(this);
        ArrayList<MyData> myList = databaseHandler.getAlldata();
        ArrayList<String> ll = new ArrayList<String>();
        for(int i=0;i<myList.size();i++)
        {
        	String s = new String();
        	s = myList.get(i).getAddress();
        	ll.add(s);
        }
        if(myList.size() > 0)
        {
	        Simple_Adapter adapter= new Simple_Adapter(this, android.R.layout.simple_list_item_1,ll);
	        LV.setAdapter(adapter);
	   
	        OnItemClickListener listener = new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View v,
						int position, long id) {
					// TODO Auto-generated method stub
					String value = (String)parent.getItemAtPosition(position);
					Intent i =new Intent(MainActivity.this,Major_Menu_Options.class);
					i.putExtra("City", value);
	  	            i.putExtra("foundLatitude",databaseHandler.getLatitude(position+1));
	  	            i.putExtra("foundLongitude",databaseHandler.getLongitude(position+1));
					startActivity(i);
				}
			};
			LV.setOnItemClickListener(listener);
			
	        alert.setView(LV);
	        
	
	        alert.show();
        }
	}
	
	public void CrashMyApp(View v)
	{
		 final ArrayList<String> ll = null;
	    
	    	 ll.add("Spanish");
		     ll.add("German");
		     String error = ll.get(0);		
	}
	
	public void Json(View v)
	{	 
		 Intent i = new Intent(this,ResultActivity.class);
         startActivity(i);
	}
	
	public void askLang(View v)
	{
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Select Language");
        final ListView LV = new ListView(this);
        
        final ArrayList<String> ll = new ArrayList<String>(10);
        ll.add(0,"Spanish");
        ll.add(1,"German");
       
        
        if(ll.size() > 0)
        {
	        Simple_Adapter adapter= new Simple_Adapter(this, android.R.layout.simple_list_item_1,ll);
	        LV.setAdapter(adapter);
	   
	        OnItemClickListener listener = new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
					// TODO Auto-generated method stub
					
					String languageToLoad;
					if(position == 0)
					{
						languageToLoad  = "sp"; // your language
					}
					
					else
					{
						languageToLoad  = "ge"; // your language
					}
					
				    Locale locale = new Locale(languageToLoad); 
				    Locale.setDefault(locale);
				    Configuration config = new Configuration();
				    config.locale = locale;
				    getBaseContext().getResources().updateConfiguration(config, 
				    getBaseContext().getResources().getDisplayMetrics());
				//    this.setContentView(R.layout.main);
				    setContentView(R.layout.activity_main);
				    
//					Intent i =new Intent(MainActivity.this,Major_Menu_Options.class);
//					i.putExtra("Language", ll.get(position));
//					startActivity(i);
				}
			};
			LV.setOnItemClickListener(listener);
			
	    alert.setView(LV);
	        
	        
        alert.show();
       
        }
	}
	
	public void PopupView(View v)
    {
    	final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Search");

        // Set an EditText view to get user input 
        final EditText input = new EditText(this);
        input.setHint("Enter Place Name");
        alert.setView(input);
        
        
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int which) {
                // the rest of your stuff
            	String value = input.getText().toString();
            	if(value.equalsIgnoreCase(""))
            		Toast.makeText(MainActivity.this, "Place cannot be empty...", Toast.LENGTH_LONG).show();
            	else
            	{
            	  // Do something with value!
	  	          Intent intent = new Intent(MainActivity.this,Major_Menu_Options.class);
	  	          
	  	          try {
	  	        	  ArrayList<Double> s = getAddressByName(value);
	  	        	  intent.putExtra("City", value);
		  	          intent.putExtra("foundLatitude",s.get(0));
		  	          intent.putExtra("foundLongitude",s.get(1));
		  	          startActivity(intent);
	  	          	} catch (Exception e) {
	  	          		// TODO: handle exception
	  	          		
	  	          		Toast.makeText(MainActivity.this, "Invalid Name of place/location...", Toast.LENGTH_LONG).show();
	  	          	}
            	}
            }
         });
        

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int whichButton) {
            // Canceled.
          }
        });
        alert.show();
    }
	
    private TextView latituteField;
	private TextView longitudeField;
	private LocationManager locationManager;
	private String provider;

	  
	  private Boolean displayGpsStatus() {
			ContentResolver contentResolver = getBaseContext().getContentResolver();
			boolean gpsStatus = android.provider.Settings.Secure.isLocationProviderEnabled(contentResolver, LocationManager.GPS_PROVIDER);
			if (gpsStatus) {
				return true;

			} else {
				return false;
			}
		}
	  
	  public void checkGPS(View v){
  		  boolean flag = displayGpsStatus();
			if (flag)
			{
				  gps = new GPSTracker(MainActivity.this);

				    // check if GPS enabled
				    if(gps.canGetLocation()){

				        double latitude = gps.getLatitude();
				        double longitude = gps.getLongitude();
				        lat = latitude;
				        longi = longitude;
				        getAddress(latitude, longitude);
				        try {
				        	if(add != null)
				        	{
					        	alertloc(add);
						        databaseHandler = new DB(getApplicationContext());
						        MyData data = new MyData();
						        data.setAddress(add);
						        data.setLat(latitude);
						        data.setLogi(longitude);
						        databaseHandler.adddata(data);
				        	}
						} catch (Exception e) {
							// TODO: handle exception
							
							Toast.makeText(getApplicationContext(), "Internet Connection not available or Server might be down ! ", Toast.LENGTH_LONG).show();
						}
				        
				    }else{
				        gps.showSettingsAlert();
				    }
			}
			else
				alertbox();
	  }
	  
	  
	  public void getAddress(double lat, double lng) {
		  
		    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.ENGLISH);
		   
		    try {
		        List<Address> addresses = geocoder.getFromLocation(lat, lng, 3);
		       
		        	Address obj = addresses.get(0);
		        	Address obj1 = addresses.get(1);
		        	
		        add = obj.getAddressLine(0);
		        add = add + ", " + obj1.getFeatureName();
		        add = add + ", " + obj.getSubLocality();
		        add = add + ", " + obj.getLocality();
		        add = add + ", " + obj.getAdminArea();
		        add = add + ", " + obj.getCountryName();
		    } catch (IOException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		        
		        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		    }
		}
	  
	  public ArrayList<Double> getAddressByName(String locationToFind) {
		  
		    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.ENGLISH);
		    ArrayList<Double> ordinates = new ArrayList<Double>();
		    try {
		        List<Address> addresses = geocoder.getFromLocationName(locationToFind, 3);
		        	if ( addresses.size() == 0)
		        		return null;
		        	Address obj = addresses.get(0);
		        	double lati = obj.getLatitude();
		        	double longi = obj.getLongitude();
		        	
		        	ordinates.add(new Double(lati));
		        	ordinates.add(new Double(longi));
		        	getAddress(lati, longi);
		        	
		        
		        // TennisAppActivity.showDialog(add);
		    } catch (IOException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		  
		        Toast.makeText(this, " Couldn't find such address / place ", Toast.LENGTH_LONG).show();
		    }
		    return ordinates;
		}
	 
	  protected void alertbox() {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Your Device's GPS is Disable")
					.setCancelable(false)
					.setTitle("** Gps Status **")
					.setPositiveButton("Gps On",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
									// finish the current activity
									// AlertBoxAdvance.this.finish();
									Intent myIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
									startActivity(myIntent);
									dialog.cancel();
								}
							})
					.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
									// cancel the dialog box
									dialog.cancel();
								}
							});
			AlertDialog alert = builder.create();
			alert.show();
		}

	  protected void alertloc(final String gloc) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(gloc)
					.setCancelable(false)
					.setTitle("** Current Location **")
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
									Intent intent = new Intent(MainActivity.this,Major_Menu_Options.class);
							        intent.putExtra("City", gloc);
							        intent.putExtra("foundLatitude",lat);
						  	        intent.putExtra("foundLongitude",longi);
							        startActivity(intent);
									dialog.cancel();
									
								}
							});
			AlertDialog alert = builder.create();
			alert.show();
	  }
	  @Override
	  public void onStatusChanged(String provider, int status, Bundle extras) {
	    // TODO Auto-generated method stub

	  }
	  @Override
	  public void onProviderEnabled(String provider) {
	    Toast.makeText(this, "Enabled new provider " + provider,
	        Toast.LENGTH_SHORT).show();

	  }
	  @Override
	  public void onProviderDisabled(String provider) {
	    Toast.makeText(this, "Disabled provider " + provider,
	        Toast.LENGTH_SHORT).show();
	  }

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}
	   
	
}
