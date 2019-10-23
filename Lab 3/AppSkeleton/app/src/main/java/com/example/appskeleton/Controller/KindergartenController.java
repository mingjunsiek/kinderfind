package com.example.appskeleton.Controller;

import com.example.appskeleton.Model.Kindergarten;

import java.util.ArrayList;

/**
 * This controller is used to handle retrieval of Kindergarten data from database as well as to allow
 * other classes to retrieve the list of kindergartens stored by the controller.
 * @author Shearman
 */

public class KindergartenController {
    /**
     * Stores an arraylist of kindergarten objects that is retrieved from the database
     */
    private ArrayList<Kindergarten> kindergartenArrayList = new ArrayList<>();

    /**
     * default constructor of kindergarten controller
     */
    public KindergartenController(){
        this.readKindergartenData();
    }

    /**
     * This function will read in the Kindergarten data stored in our database and each kindergarten will
     * become a kindergarten object with their various attributes like centre code, address, contact no. etc
     * These kindergarten objects will then be stored in kindergartenArrayList
     */
    public void readKindergartenData() {

    }

    /**
     * This function will take in an ArrayList of Kindergarten objects that is passed in as a parameter
     * to the function. This function is used by the Search Controller to update the list of Kindergartens
     * after filtering based on search string results. The function then update class attribute kindergartenArrayList
     * @param filteredList filtered ArrayList of Kindergartens
     */
    public void filterKindergartenList(ArrayList<Kindergarten> filteredList){

    }

    /**
     * This function is a public function for other classes to retrieve the ArrayList of Kindergarten
     * from the KindergartenController.
     * @return kindergartenArrayList ArrayList of kindergartens
     */
    public ArrayList<Kindergarten> getKindergartenArrayList(){
        return kindergartenArrayList;
    }
}
