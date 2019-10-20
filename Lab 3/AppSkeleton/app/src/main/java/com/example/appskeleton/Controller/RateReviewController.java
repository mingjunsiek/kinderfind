package com.example.appskeleton.Controller;

import com.example.appskeleton.Model.RateReview;

/**
 * This controller handles the insertion and updating of ratings & review done by the user.
 * It is also used for retrieving the user's ratings & review for a specific kindergarten.
 */
public class RateReviewController {

    /**
     * RateReview object to store the user's ratings & review information.
     */
    private RateReview rateReview;

    /**
     * Default constructor for this controller.
     * Inside this constructor will contain the database connection to ratings & review.
     * @param centre_code This argument is used to initialized the database connection which
     *                    points to the specific kindergarten's rating_review table in the database
     */
    public RateReviewController(String centre_code){}

    /**
     * This function is only called if the user have not rate & review this kindergarten yet.
     * This function initialize the rateReview object with the arguments passed and additional information.
     * It then insert the rateReview object into the database using user_id as key.
     * @param user_id Used as a key to uniquely identify the ratings and review in the database
     */
    public void addReview(String user_id, double amenitiesRating, double cleanlinessRating,
        double manpowerRating, double curriculumRating, String review){}

    /**
     * This function is only called if the user already rated and reviewed this kindergarten.
     * This function updates the ratings and review into the database based on the kindergarten and user.
     * First, it initialize the rateReview object with the arguments passed and additional information.
     * Second, it updates the ratings and review in the database using user_id as key.
     * @param user_id Used to identify which rating and review to update in the database.
     */
    public void updateReview(String user_id, double amenitiesRating, double cleanlinessRating,
         double manpowerRating, double curriculumRating, String review){}

    /**
     * This function retrieves the ratings and review based on the kindergarten and user.
     * If there is a ratings and review, we initialize rateReview with the retrieved information.
     * Else, we set rateReview to null.
     * @param user_id
     */
    public RateReview getRateReview(String user_id){
        return rateReview;
    }
}
