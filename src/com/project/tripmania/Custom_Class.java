package com.project.tripmania;

import android.graphics.drawable.Drawable;

public class Custom_Class {
	String img_id;
	String heading;
	String reference;
	
	public Custom_Class(String id, String h,String ref)
	{
		img_id = id;
		heading = h;
		reference = ref;
	}
	
	public void setImgId(String id)
	{
		img_id = id;
	}
	
	public void setReference(String ref)
	{
		reference = ref;
	}
	
	public void setHeading(String head)
	{
		heading = head;
	}
	
	public String getImgId()
	{
		return img_id;
	}
	
	public String getHeading()
	{
		return heading;
	}
	public String getReference()
	{
		return reference;
	}
}
