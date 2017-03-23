package com.example.android.quakereport_udacity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mikem on 3/20/2017.
 */

public class Earthquake {

    private double mMagnitude;
    private String mLocation;
    private long mTimeInMilliseconds;
    private String mWebsiteURL;

    public Earthquake(double magnitude, String location, long timeInMilliseconds, String websiteURL){
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds  = timeInMilliseconds;
        mWebsiteURL = websiteURL;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getTimeInMilliseconds() { return mTimeInMilliseconds; }

    public String getWebsiteURL(){ return mWebsiteURL; }
}
