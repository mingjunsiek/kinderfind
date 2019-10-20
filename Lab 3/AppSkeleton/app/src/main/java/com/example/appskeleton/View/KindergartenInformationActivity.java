package com.example.appskeleton.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.appskeleton.Controller.InformationController;
import com.example.appskeleton.Model.Kindergarten;
import com.example.appskeleton.Model.KindergartenServices;
import com.example.appskeleton.Model.RateReview;
import com.example.appskeleton.R;

import java.util.ArrayList;

/**
 * When user taps on a kindergarten name in the ListViewActivity interface, the app will bring the user
 * to this KindergartenInformationActivity interface where the users can see detailed information regarding
 * the kindergarten they selected like, the ratings and reviews, address, location etc.
 * @author Shearman
 */

public class KindergartenInformationActivity extends AppCompatActivity {

    /**
     * Stores an ArrayList of kindergartenServices objects based on the input kindergarten centre code
     */
    private ArrayList<KindergartenServices> kindergartenServicesArrayList = new ArrayList<>();
    /**
     * Stores an ArrayList of ratings and reviews objects based on the input kindergarten centre code
     */
    private ArrayList<RateReview> rateReviewArrayList = new ArrayList<>();
    /**
     * Stores a Kindergarten object
     */
    private Kindergarten kindergarten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kindergarten_information);
    }

    /**
     * default constructor of KindergartenInformationActivity taking in Kindergarten object as argument
     * @param kindergarten
     */
    public KindergartenInformationActivity(Kindergarten kindergarten){
        this.kindergarten = kindergarten;
        InformationController informationController = new InformationController(kindergarten);
        kindergartenServicesArrayList = informationController.getKindergartenServicesArrayList();
        rateReviewArrayList = informationController.getRateReviewArrayList();

    }

    /**
     * displays all the ratings and reviews of the selected kindergarten
     */
    public void displayRatingReviews(){

    }

    /**
     * display the location of the kindergarten as a marker on google maps
     */
    public void displayLocation(){

    }

    /**
     * displays information regarding the kindergarten like kindergarten services, opening hours, address,
     * programmes offered etc to the user.
     */
    public void displayKindergartenInformation(){

    }

    /**
     * Allows the user to share the kindergarten with others through social media platforms
     */
    public void shareKindergarten(){

    }

    /**
     * When the user taps on the Rate/Review button, this function will be activated and the centre code
     * of the selected kindergarten is passed as an argument to the function and the function will bring the
     * user to the RateReviewActivity interface
     * @param centreCode
     */
    public void tapOnRateReview(String centreCode){

    }

}
