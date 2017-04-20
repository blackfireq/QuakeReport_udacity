package com.example.android.quakereport_udacity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
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

    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        new EarthquakeTask().execute(USGS_REQUEST_URL);


    }
        private void updateUI(ArrayList<Earthquake> earthquakes){
            // Find a reference to the {@link ListView} in the layout
            ListView earthquakeListView = (ListView)findViewById(R.id.list);

            // Create a new {@link ArrayAdapter} of earthquakes
            final EarthquakeAdapter mAdapter = new EarthquakeAdapter(this, earthquakes);

            // Set the adapter on the {@link ListView}
            // so the list can be populated in the user interface
            earthquakeListView.setAdapter(mAdapter);



            // set onclicklistener to send to the webpage of the earthquake thats clicked on
            // Set a click listener to play the audio when the list item is clicked on
            earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                    // Get the {@link Earthquake} object at the given position the user clicked on
                    Earthquake currentQuake = mAdapter.getItem(position);

                    // Convert the String URL into a URI object (to pass into the Intent constructor)
                    Uri webUrl = Uri.parse(currentQuake.getWebsiteURL());

                    Intent browserIntent = new Intent(Intent.ACTION_VIEW,webUrl);
                    startActivity(browserIntent);

                }
            });
        }

    private class EarthquakeTask extends AsyncTask<String,Void,ArrayList<Earthquake>> {

        @Override
        protected ArrayList<Earthquake> doInBackground(String... url) {
            // Create a fake list of earthquake locations.
            ArrayList<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(url[0]);
            return earthquakes;
        }

        @Override
        protected void onPostExecute(ArrayList<Earthquake> earthquakes) {
            super.onPostExecute(earthquakes);
            updateUI(earthquakes);
        }
    }
}
