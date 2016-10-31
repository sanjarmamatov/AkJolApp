package com.example.user.akjoll;

/**
 * Created by User on 04.10.2016.
 */
public class GeoMarker {
    private Double latitude;
    private Double longtitude;

    GeoMarker() {
    }

    public GeoMarker(double latitude, double longtitude) {
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude() {
        this.latitude = latitude;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude() {
        this.longtitude = longtitude;
    }

}
