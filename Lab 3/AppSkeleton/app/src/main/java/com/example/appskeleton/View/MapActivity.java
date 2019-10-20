package com.example.appskeleton.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.appskeleton.R;

import com.example.appskeleton.Model.Marker;
import java.util.ArrayList;

/**
 * When the user successfully log in to the application or successfully register an account with the app,
 * the user is brought to this interface which displays the map of Singapore with location markers of
 * various kindergartens in Singapore. The map also displays the current location of the user if user's
 * location permission is granted. This is the default main interface of the app.
 * @author Shearman 
 */

public class MapActivity extends AppCompatActivity {

    /**
     * stores an ArrayList of markers to be displayed on the map generated from map controller.
     */
    private ArrayList<Marker> markers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }

    /**
     * This function plots and display the Markers stored in markers ArrayList on the map
     */
    public void displayMarkers(){

    }

    /**
     * This function plots and display the Marker of the current device location on the map
     */
    public void displayCurrentLocation(){

    }

}
