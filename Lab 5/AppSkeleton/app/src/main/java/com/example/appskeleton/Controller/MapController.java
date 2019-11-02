package com.example.appskeleton.Controller;

import com.example.appskeleton.Model.Kindergarten;
import com.example.appskeleton.Model.Marker;

import java.util.ArrayList;

/**
 * This controller is used to generate the map interface markers as well as handle location related
 * functions
 * @author Shearman
 */

public class MapController {

    /**
     * stores an ArrayList of Kindergartens retrieved from the Kindergarten controller
     */
    private ArrayList<Kindergarten> kindergartens = new ArrayList<>();
    /**
     * stores an ArrayList of markers to be displayed on the map generated from the locations of kindergartens
     * stored in kindergartens ArrayList.
     */
    private ArrayList<Marker> markers = new ArrayList<>();
    /**
     * stores the longitude and latitude of the current device location if permission is granted by the
     * user to access their location
     */
    private double currentLatitude,currentLongitude;
    /**
     * stores the marker of the current location of the device
     */
    private Marker currentLocationMarker;

    /**
     * default constructor of MapController
     */
    public MapController(){
        KindergartenController kc = new KindergartenController();
        this.generateMarkers();
        this.getCurrentLocation();
    }

    /**
     * this function is used to generate markers to be displayed on the map interface to show the locations
     * of the kindergartens on the map
     */
    public void generateMarkers(){

    }

    /**
     * This function gets permission from the user to acquire their current location and stores their
     * location information in currentLatitude,currentLongitude as well as create the currentLocationMarker.
     * If user does not allow their location to be given to the app, the currentLocationMarker will not
     * be generated.
     */
    public void getCurrentLocation(){

    }

    /**
     * This function returns the marker of the current device location to the calling function.
     * @return the location marker of the current device location
     */
    public Marker getCurrentLocationMarker(){
        return currentLocationMarker;
    }

    /**
     * This function returns the ArrayList markers of various kindergarten locations around Singapore
     * to the calling function.
     * @return an ArrayList of markers of various kindergarten locations around Singapore
     */
    public ArrayList<Marker> getMarkers(){
        return markers;
    }

}
