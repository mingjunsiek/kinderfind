package com.example.appskeleton.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.appskeleton.Controller.RateReviewController;
import com.example.appskeleton.Controller.SessionController;
import com.example.appskeleton.Model.RateReview;
import com.example.appskeleton.Model.User;
import com.example.appskeleton.R;

public class RateReviewActivity extends AppCompatActivity {

    /**
     * The current SessionController is initialized to sessionController.
     */
    private static SessionController sessionController;
    private RateReviewController rateReviewController;
    /**
     * This function creates the following:
     * 4 rating bars for amenities, cleanliness, manpower and curriculum.
     * 1 text field for review
     * 2 buttons: 1 for submit and 1 for update.
     * Only 1 button will appear depending on the context of the situation
     * This function initialize
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_review);
    }

    /**
     * This function retrieves the current user from session. This is used to identify the user
     * when they are submitting the ratings and review.
     * @return
     */
    public User getCurrentUser(){
        return sessionController.getCurrentUser();
    }

    /**
     * This function is called when the Submit button is clicked. It calls the addReview()
     * function in the RateReviewController.
     */
    public void submitRateReview(){}

    /**
     * This function is called when the Update button is clicked. It calls the updateReview()
     * function in the RateReviewController.
     */
    public void updateRateReview(){}

}
