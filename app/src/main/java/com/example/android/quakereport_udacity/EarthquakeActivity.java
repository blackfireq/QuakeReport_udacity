package com.example.android.quakereport_udacity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        final ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();
        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView)findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);



        // set onclicklistener to send to the webpage of the earthquake thats clicked on
        // Set a click listener to play the audio when the list item is clicked on
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Get the {@link Earthquake} object at the given position the user clicked on
                Earthquake currentQuake = earthquakes.get(position);

                String webUrl = currentQuake.getWebsiteURL();

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(webUrl));
                startActivity(browserIntent);

            }
        });
    }
}
