package com.example.appskeleton.Controller;

import com.example.appskeleton.Model.Kindergarten;
import com.example.appskeleton.Model.KindergartenServices;
import com.example.appskeleton.Model.RateReview;

import java.util.ArrayList;

/**
 * This controller is used to handle and retrieve information like ratings and review, kindergarten
 * services for a particular kindergarten based on input centre code.
 * @author Shearman
 */
public class InformationController {
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

    /**
     * This function will read in the KindergartenServices data stored in our database and each kindergarten
     * service will become a kindergartenService object with their various attributes like centre name,
     * fees, programme hours. etc. These kindergartenServices objects will then be stored in kindergartenServicesArrayList
     * @param centreCode input centreCode for particular kindergarten
     */
    public void readKindergartenServices(String centreCode) {

    }

    /**
     * This function will read in the Rating and review data stored in our database and each rating
     * and review by an individual user will become a RateReview object. These RateReview objects will
     * then be stored in rateReviewArrayList
     * @param centreCode input centreCode for particular kindergarten
     */
    public void readRatingsReview(String centreCode) {

    }
    /**
     * This function is a public function for other classes to retrieve the ArrayList of KindergartenServices
     * from the InformationController.
     * @return kindergartenServicesArrayList ArrayList of kindergartenServices
     */
    public ArrayList<KindergartenServices> getKindergartenServicesArrayList(){
        return kindergartenServicesArrayList;
    }
    /**
     * This function is a public function for other classes to retrieve the ArrayList of RateReview Objects
     * from the InformationController.
     * @return rateReviewArrayList ArrayList of Rating and reviews
     */
    public ArrayList<RateReview> getRateReviewArrayList(){
        return rateReviewArrayList;
    }

}
