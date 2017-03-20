package com.example.android.quakereport_udacity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();
//        earthquakes.add(new Earthquake("4.6","San Francisco","Feb 2, 1998"));
//        earthquakes.add(new Earthquake("3.6","Fiji","Aug 13, 1998"));
//        earthquakes.add(new Earthquake("4.2","Japan","Sep 2, 1973"));
//        earthquakes.add(new Earthquake("3.8","New York","Dec 17, 1991"));
//        earthquakes.add(new Earthquake("2.5","The Moon","Nov 28, 2001"));
//        earthquakes.add(new Earthquake("4.5","England","Jul 2, 2008"));
//        earthquakes.add(new Earthquake("4.1","Texas","May 12, 1958"));

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView)findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);
    }
}
