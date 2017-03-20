package com.example.android.quakereport_udacity;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mikem on 3/20/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake>{

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> Earthquake) {
        super(context, 0, Earthquake);
    }
    //override the getview to diplay the layout
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        //get the position of the current object in the ArrayList
        Earthquake currentQuake = getItem(position);

        //get the view that will display the magnitude
        TextView magnitudeView = (TextView)convertView.findViewById(R.id.magnitude_view);

        //set the text of the view that will display the magnitude
        magnitudeView.setText(Double.toString(currentQuake.getMagnitude()));

        //get the view that will display the location
        TextView locationView = (TextView)convertView.findViewById(R.id.location_view);

        //set the text of the view that will display the location
        locationView.setText(currentQuake.getLocation());
        //get the view that will display the date
        TextView dateView = (TextView)convertView.findViewById(R.id.date_view);

        //set the text of the view that will display the date
        dateView.setText(currentQuake.getDate());

        return convertView;
    }

}
