package com.example.android.quakereport_udacity;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
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
import java.util.List;

import static android.media.CamcorderProfile.get;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>>{

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10";

    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int EARTHQUAKE_LOADER_ID = 1;

    /** Adapter for the list of earthquakes */
    private EarthquakeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);



        // new EarthquakeTask().execute(USGS_REQUEST_URL);
        // Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager loaderManager = getLoaderManager();

        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);




            // Find a reference to the {@link ListView} in the layout
            ListView earthquakeListView = (ListView)findViewById(R.id.list);

            // Create a new {@link ArrayAdapter} of earthquakes
            mAdapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());

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

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new EarthquakeLoader(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakes) {
        mAdapter.clear();

        if (earthquakes != null && !earthquakes.isEmpty()) {
            mAdapter.addAll(earthquakes);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        mAdapter.clear();
    }
}
