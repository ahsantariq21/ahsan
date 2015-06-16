package com.project.tripmania;

public class MyData {
	String Address;
	double longi;
	double lat;
	
	public MyData(){}
	public MyData(String address,double longi,double lat)
	{
		Address = address;
		this.longi = longi;
		this.lat = lat;
	}
	
	public void setAddress(String address)
	{
		Address = address;
	}
	public void setLogi(double longi)
	{
		this.longi = longi;
	}
	public void setLat(double lat)
	{
		this.lat = lat;
	}
	
	public String getAddress()
	{
		return Address;
	}
	
	public double getLongi()
	{
		return longi;
	}
	public double getLat()
	{
		return lat;
	}

}
