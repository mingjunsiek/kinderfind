package com.example.appskeleton.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.appskeleton.Model.Kindergarten;
import com.example.appskeleton.R;
import com.example.appskeleton.Controller.SearchController;

import java.util.ArrayList;

/**
 * This interface is embedded inside of MapActivity and ListViewActivity as a search bar for users to
 * input a search string and filter out a list of kindergarten based on input search string by user.
 * @author Shearman
 */

public class SearchActivity extends AppCompatActivity {

    /**
     * string to store the user input typed into search bar
     */
    private String searchString;
    /**
     * Stores a filtered ArrayList of kindergarten objects that is filtered based on search string
     */
    private ArrayList<Kindergarten> searchResults = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    /**
     * default constructor of search activity interface
     */
    public SearchActivity(){
        this.displaySearchBar();
    }

    /**
     * displays the search bar in the application for the user to input a search string to get a filtered
     * list of kindergartens based on search string
     */
    public void displaySearchBar(){

    }

    /**
     * displays a list of kindergartens relevant to the input search string
     */
    public void displaySearchResults(){

    }

    /**
     * use the search string input by the user to call the search controller to filter out a list of
     * kindergartens based on input search string
     */
    public void filterResults(){
        SearchController searchController = new SearchController();
        searchResults = searchController.searchKindergarten(searchString);
        this.displaySearchResults();
    }
}
