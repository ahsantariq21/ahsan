package com.project.tripmania;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class Simple_Adapter extends ArrayAdapter<String> {
	Context c;
	int XML_id;
    public Simple_Adapter(Context context, int resID, ArrayList<String> items) {
        super(context, resID, items);    
        c= context;
        XML_id = resID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        return v;
    }

}
