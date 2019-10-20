package com.example.appskeleton.Controller;

import com.example.appskeleton.Model.Kindergarten;
import java.util.ArrayList;

/**
 * This controller is used filter and get relevant Kindergarten objects based on the search string entered
 * by users in the search bar and store and return a filtered ArrayList of Kindergarten objects as the Search
 * results.
 * @author Shearman
 */

public class SearchController {

    /**
     * Stores a filtered ArrayList of kindergarten objects that is filtered based on search string
     */
    private ArrayList<Kindergarten> searchResults = new ArrayList<>();

    /**
     * This function takes in a string as a parameter. The string is input by user which can be a Kindergarten's
     * address, centre code, centre name or a location name. The function then generates a filtered ArrayList
     * of Kindergarten objects based on search string and returns it as well as store it as a local attribute.
     * @param searchString
     * @return an ArrayList of Kindergarten objects filtered based on search string by user
     */
    public ArrayList<Kindergarten> searchKindergarten(String searchString){
        return searchResults;
    }

    /**
     * Returns an ArrayList of Kindergarten objects filtered based on search string by user when called
     * @return an ArrayList of Kindergarten objects filtered based on search string by user
     */
    public ArrayList<Kindergarten> getSearchResults(){
        return searchResults;
    }
}
