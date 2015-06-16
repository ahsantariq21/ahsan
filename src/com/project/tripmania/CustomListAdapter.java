package com.project.tripmania;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomListAdapter extends ArrayAdapter<Custom_Class>{
	public Context con;
	int XML_id;
	ArrayList <Custom_Class> Array;
	
	
	public CustomListAdapter(Context c,int XMLId, ArrayList<Custom_Class> myArray)
	{
		super(c, XMLId, myArray);
		con = c;
		XML_id = XMLId;
		Array = myArray;
	}
	
	public View getView(int position, View convertView, ViewGroup parent)
	{
		Animation animator = null;
		View row;
		if(convertView == null)
		{
			LayoutInflater inflater = ((Activity)con).getLayoutInflater();
			row = inflater.inflate(XML_id, parent, false);
		}
		else
		{
			row = (View) convertView;
		}
		ImageView view1 = (ImageView) row.findViewById(R.id.countryInfo);
		
		new ImgDownload(Array.get(position).getImgId(),view1,con).execute();
		
		
		
//		view.setImageDrawable(Array.get(position).getImgId());
		
		TextView headView = (TextView) row.findViewById(R.id.name);
		headView.setText(Array.get(position).getHeading());
		headView.setTextColor(Color.WHITE);
		
		TextView refView = (TextView) row.findViewById(R.id.reference);
		refView.setText(Array.get(position).getReference());
	
//		 animator = AnimationUtils.loadAnimation(con, R.anim.pushin_animation);
//		 animator.setDuration(500);
//		 row.startAnimation(animator);
//		 animator = null;

		return row;
	}
}

class ImgDownload extends AsyncTask<Object,Object,String> {
	private Context c ;
    private String requestUrl;
    private ImageView view;
    private Bitmap pic;

    ImgDownload(String requestUrl, ImageView view ,Context c) {
        this.requestUrl = requestUrl;
        this.view = view;
        this.c =c;  
    }

    @Override
    protected String doInBackground(Object... objects) {
        try {
            URL url = new URL(requestUrl);
            URLConnection conn = url.openConnection();
            pic = BitmapFactory.decodeStream(conn.getInputStream());
            return "Downloaded";
        } catch (Exception ex) {
        }
        return null;
    }

    @Override
    protected void onPostExecute(String o) {
        view.setImageBitmap(pic);
    }
}
