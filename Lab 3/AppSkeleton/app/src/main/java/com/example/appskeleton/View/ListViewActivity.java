package com.example.appskeleton.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.appskeleton.Controller.KindergartenController;
import com.example.appskeleton.View.KindergartenInformationActivity;
import com.example.appskeleton.Model.Kindergarten;
import com.example.appskeleton.R;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    /**
     * Stores an ArrayList of kindergarten objects that is retrieved from KindergartenController
     */
    private ArrayList<Kindergarten> kindergartenList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
    }

    /**
     * default constructor of ListViewActivity to retrieve ArrayList of Kindergarten from Kindergarten
     * Controller.
     */
    public ListViewActivity(){
        this.generateKindergartenList();
    }

    /**
     * Display a list of Kindergartens on the screen of device
     */
    public void displayKindergartenList(){

    }

    /**
     * Function to retrieve ArrayList of Kindergarten from Kindergarten Controller.
     */
    public void generateKindergartenList(){
        KindergartenController kindergartenController = new KindergartenController();
        kindergartenList = kindergartenController.getKindergartenArrayList();
    }

    /**
     * when user taps on a kindergarten on the list of kindergartens on the interface, the user is brought
     * to the KindergartenInformationActivity interface to view detailed information of the selected Kindergarten.
     * @param kindergarten
     */
    public void tapOnKindergarten(Kindergarten kindergarten){

    }

}
