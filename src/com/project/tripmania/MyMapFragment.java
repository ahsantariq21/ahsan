package com.project.tripmania;

import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

public class MyMapFragment extends SupportMapFragment {
  private MapCallback callback;

  public void setMapCallback(MapCallback callback)
  {
    this.callback = callback;
  }

  public static interface MapCallback
  {
     public void onMapReady(GoogleMap map);
  }

  @Override public void onActivityCreated(Bundle savedInstanceState)
  {
     if(callback != null) callback.onMapReady(getMap());     
  }
}