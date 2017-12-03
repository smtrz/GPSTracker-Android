package com.highbryds.gpstracker.Model;

/**
 * Created by tahir.raza on 11/26/2017.
 */

public class TrackMe_Location {

    public double latitude;
    public double longitude;
    public String AddOn;

    public String getAddOn() {
        return AddOn;
    }

    public void setAddOn(String addOn) {
        AddOn = addOn;
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
