package com.ewu.bug.swv.model;

/**
 * Created by Xplo on 2/21/2016.
 */
public class GPSLoc {

    double latitude;
    double longitude;



    public GPSLoc() {

    }

    public GPSLoc(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "GPSLoc{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
