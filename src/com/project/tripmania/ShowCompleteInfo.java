package com.project.tripmania;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.analytics.tracking.android.EasyTracker;

public class ShowCompleteInfo extends Activity {
	
	private String messageBody;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_complete_info);
		
		Intent i = getIntent();
		if(i != null)
		{
			String name = i.getStringExtra("Name");
			messageBody = "Hey!!\n Lets make a plan to visit "+name;
			
			TextView t = (TextView) findViewById(R.id.complete_heading);
			t.setText(name.toString());
			
			Animation animator = AnimationUtils.loadAnimation(this, R.anim.left_to_right_in);
			
			TextView names = (TextView) findViewById(R.id.AppsName_id);
			TextView head = (TextView) findViewById(R.id.complete_heading);
			TextView conHead = (TextView) findViewById(R.id.complete_number_heading);
			TextView con = (TextView) findViewById(R.id.Complete_number);
			TextView webHead = (TextView) findViewById(R.id.complete_website_heading);
			TextView web = (TextView) findViewById(R.id.compete_website);
			
			ImageView image = (ImageView) findViewById(R.id.logo2);
			
			
			animator.setDuration(700);
			names.startAnimation(animator);
			animator.setDuration(700);
			head.startAnimation(animator);
			animator.setDuration(700);
			conHead.startAnimation(animator);
			animator.setDuration(700);
			con.startAnimation(animator);
			animator.setDuration(700);
			webHead.startAnimation(animator);
			animator.setDuration(700);
			web.startAnimation(animator);
			animator.setDuration(700);
			
			animator.setDuration(700);
			image.startAnimation(animator);
			
			animator = AnimationUtils.loadAnimation(this, R.anim.fade_in);
			animator.setDuration(700);
			names.startAnimation(animator);
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_complete_info, menu);
		return true;
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
	         Toast.makeText(ShowCompleteInfo.this,"There is no email client installed.", Toast.LENGTH_SHORT).show();
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
}
