package com.example.android.quakereport_udacity;

import android.app.Activity;
import android.location.Location;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.content.ContextCompat;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import android.graphics.drawable.GradientDrawable;

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
        //create the formatted output for magnitude_view
        String formattedMagnitude = formatMagnitude(currentQuake.getMagnitude());

        //set the text in the magnitude_view
        magnitudeView.setText(formattedMagnitude);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentQuake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        // get time and format it into 2 string array
        String[] locationArray  = formatLocation(currentQuake.getLocation());

        //get the view that will display the  offset location
        TextView offsetLocationView = (TextView)convertView.findViewById(R.id.location_offset_view);

        //set the text of the view that will display the offset location
        offsetLocationView.setText(locationArray[0]);

        //get the view that will display the  primary location
        TextView primaryLocationView = (TextView)convertView.findViewById(R.id.location_primary_view);

        //set the text of the view that will display the primary location
        primaryLocationView.setText(locationArray[1]);

        // Create a new Date object from the time in milliseconds of the earth
        Date dateObject = new Date(currentQuake.getTimeInMilliseconds());

        //get the view that will display the date
        TextView dateView = (TextView)convertView.findViewById(R.id.date_view);

        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) convertView.findViewById(R.id.time_view);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);

        return convertView;
    }

    /**
     * return the color for the severity of the earthquake
     */
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    //check for keywords to split string
    // return array with the split location
    private String[] formatLocation(String location){
        String[] locationArray = new String[2];
        int offsetLocation = location.indexOf("of");
        // needs work ************************************************
        if (offsetLocation > -1){
            locationArray[0] = location.substring(0,offsetLocation+2);
            locationArray[1] = location.substring(offsetLocation+2);
        } else {
            locationArray[0] = "Near the";
            locationArray[1] = location;
        }

        return locationArray;
    }
}
