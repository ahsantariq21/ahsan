package com.project.tripmania;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.google.analytics.tracking.android.EasyTracker;

public class List_view_info extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view_info);
		
		Intent intent = getIntent();
		ArrayList<Custom_Class> hotels = new ArrayList<Custom_Class>();

		CustomListAdapter adapter = new CustomListAdapter(this, R.layout.custom_list_layout, hotels);
		ListView LV = (ListView) findViewById(R.id.custom_list);
		LV.setAdapter(adapter);
		
		OnItemClickListener listener = new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView parent, View v, int position,
					long id) {
				TextView t = (TextView) findViewById(R.id.heading_id);
				String name = t.getText().toString();
				Intent I = new Intent(List_view_info.this,ShowCompleteInfo.class);
				I.putExtra("Name", name);
				startActivity(I);
			}
		};
		LV.setOnItemClickListener(listener);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_view_info, menu);
		return true;
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

}
