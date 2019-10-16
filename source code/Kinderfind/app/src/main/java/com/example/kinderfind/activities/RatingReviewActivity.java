package com.example.kinderfind.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kinderfind.R;
import com.example.kinderfind.controller.RatingReviewController;
import com.example.kinderfind.models.RatingReview;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ServerValue;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class RatingReviewActivity extends AppCompatActivity {
    public static String centre_code;
    private MaterialRatingBar cleanlinessRatingBar, manpowerRatingBar, curriculumRatingBar, amenitiesRatingBar;
    private EditText reviewEditText;
    private Button submitButton;
    private FirebaseAuth auth;
    private boolean ratingReviewExist = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Rate/Review");
        setContentView(R.layout.activity_rating_review);
        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = auth.getCurrentUser();
        cleanlinessRatingBar = findViewById(R.id.rating_cleanliness);
        manpowerRatingBar = findViewById(R.id.rating_manpower);
        curriculumRatingBar = findViewById(R.id.rating_curriculum);
        amenitiesRatingBar = findViewById(R.id.rating_amenities);

        cleanlinessRatingBar.setMax(5);
        manpowerRatingBar.setMax(5);
        curriculumRatingBar.setMax(5);
        amenitiesRatingBar.setMax(5);

        reviewEditText = findViewById(R.id.review_edit_text);
        submitButton = findViewById(R.id.rating_submit);

        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                RatingReview ratingReview = new RatingReview();
                ratingReview.setAmenities_rating(amenitiesRatingBar.getRating());
                ratingReview.setCleanliness_rating(cleanlinessRatingBar.getRating());
                ratingReview.setCurriculum_rating(curriculumRatingBar.getRating());
                ratingReview.setManpower_rating(manpowerRatingBar.getRating());
                ratingReview.setReview(reviewEditText.getText().toString().trim());
                ratingReview.setTotal_rating((amenitiesRatingBar.getRating() + cleanlinessRatingBar.getRating()
                        + curriculumRatingBar.getRating() + manpowerRatingBar.getRating()));
                ratingReview.setUser_id(user.getUid());
                ratingReview.setUsername(user.getDisplayName());
                ratingReview.setTimestamp(Calendar.getInstance().getTimeInMillis());

                if(amenitiesRatingBar.getRating() == 0 || cleanlinessRatingBar.getRating() == 0
                        || curriculumRatingBar.getRating() == 0 || manpowerRatingBar.getRating() == 0)
                    Toast.makeText(RatingReviewActivity.this, "Please fill up all ratings", Toast.LENGTH_SHORT).show();
                else if(reviewEditText.getText().toString().trim() == "")
                    Toast.makeText(RatingReviewActivity.this, "Please fill up review", Toast.LENGTH_SHORT).show();
                else if(!ratingReviewExist) {
                    new RatingReviewController(centre_code).addRatingReview(ratingReview, user.getUid() ,new RatingReviewController.DataStatus() {
                        @Override
                        public void DataIsLoaded(RatingReview ratingReviews, List<String> keys) {}

                        @Override
                        public void DataIsInserted() {
                            Toast.makeText(RatingReviewActivity.this, "Ratings & Review has been inserted successfully", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }

                        @Override
                        public void DataIsUpdated() {}

                        @Override
                        public void DataIsDeleted() {}
                    });
                }
                else{
                    new RatingReviewController(centre_code).updateRatingReview(ratingReview, user.getUid(), new RatingReviewController.DataStatus() {
                        @Override
                        public void DataIsLoaded(RatingReview ratingReviews, List<String> keys) {}

                        @Override
                        public void DataIsInserted() {}

                        @Override
                        public void DataIsUpdated() {
                            Toast.makeText(RatingReviewActivity.this, "Ratings & Review has been updated successfully", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }

                        @Override
                        public void DataIsDeleted() {}
                    });
                }
            }
        });

        new RatingReviewController(centre_code).readRatingReview(user.getUid(), new RatingReviewController.DataStatus() {
            @Override
            public void DataIsLoaded(RatingReview ratingReviews, List<String> keys) {
                if(ratingReviews != null) {
                    ratingReviewExist = true;
                    cleanlinessRatingBar.setRating((float)(ratingReviews.getCleanliness_rating()));
                    manpowerRatingBar.setRating((float)(ratingReviews.getManpower_rating()));
                    curriculumRatingBar.setRating((float)(ratingReviews.getCurriculum_rating()));
                    amenitiesRatingBar.setRating((float)(ratingReviews.getAmenities_rating()));
                    reviewEditText.setText(ratingReviews.getReview());
                }
            }
            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
}
