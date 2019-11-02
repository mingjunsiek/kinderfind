package com.example.appskeleton.Model;

/**
 * This class implements the Marker entity with the following attributes:
 *      longitude, latitude.
 * @author Ming Jun
 */

public class Marker {
    private double longitude, latitude;

    public Marker(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * Below are the Getters and Setters for each attribute.
     */

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
