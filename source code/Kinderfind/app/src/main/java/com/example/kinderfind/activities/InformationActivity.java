package com.example.kinderfind.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

import android.app.Activity;
import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.kinderfind.R;
import com.example.kinderfind.adapters.KindergartenServicesAdapter;
import com.example.kinderfind.adapters.RatingReviewAdapter;
import com.example.kinderfind.controller.InformationController;
import com.example.kinderfind.models.Kindergarten;
import com.example.kinderfind.models.KindergartenServices;
import com.example.kinderfind.models.RatingReview;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class InformationActivity extends AppCompatActivity {
    public static Kindergarten kindergarten;
    private TextView centerNameTV, centerAddressTV, centerContactTV, centerEmailTV, centerWebsiteTV, centerCertifiedTV;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        auth = FirebaseAuth.getInstance();

        //final FirebaseUser user = auth.getCurrentUser();

        final MaterialRatingBar cleanlinessRatingBar = findViewById(R.id.info_cleaniness);
        final MaterialRatingBar manpowerRatingBar = findViewById(R.id.info_manpower);
        final MaterialRatingBar curriculumRatingBar = findViewById(R.id.info_curriculum);
        final MaterialRatingBar amenitiesRatingBar = findViewById(R.id.info_facilities);

        setTitle(kindergarten.getCentre_name());
        centerNameTV = findViewById(R.id.center_name);
        centerAddressTV = findViewById(R.id.center_address);
        centerContactTV = findViewById(R.id.center_contact);
        centerEmailTV = findViewById(R.id.center_email);
        centerWebsiteTV = findViewById(R.id.center_website);
        centerCertifiedTV = findViewById(R.id.center_certified);

        centerNameTV.setText(kindergarten.getCentre_name());
        centerAddressTV.setText(kindergarten.getCenter_address());
        centerContactTV.setText(kindergarten.getCentre_contact_no());
        centerEmailTV.setText(kindergarten.getCentre_email_address());
        centerWebsiteTV.setText(kindergarten.getCentre_website());
        centerCertifiedTV.setText(kindergarten.getSpark_certified());

        InformationController informationController = new InformationController(kindergarten.getCenter_code());

        informationController.readKindergarten(new InformationController.KindergartenDataStatus() {
            @Override
            public void DataIsLoaded(List<KindergartenServices> kindergartens, List<String> keys) {
                RecyclerView recyclerView = findViewById(R.id.services_recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(InformationActivity.this));
                recyclerView.setHasFixedSize(true);
                KindergartenServicesAdapter adapter = new KindergartenServicesAdapter(kindergartens, keys);
                recyclerView.setAdapter(adapter);
            }
        });

        informationController.readRatingReview(new InformationController.RatingReviewDataStatus() {
            @Override
            public void DataIsLoaded(List<RatingReview> ratingReviews, List<String> keys) {
                System.out.println("Rating: "+ratingReviews);
                RecyclerView recyclerView = findViewById(R.id.review_recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(InformationActivity.this));
                recyclerView.setHasFixedSize(true);
                RatingReviewAdapter adapter = new RatingReviewAdapter(ratingReviews, keys, InformationActivity.this);
                recyclerView.setAdapter(adapter);

                double amenities_rating = 0;
                double cleaniness_rating = 0;
                double manpower_rating = 0;
                double curriculum_rating = 0;
                int ratingSize = ratingReviews.size();
                TextView avgTv = findViewById(R.id.info_avg);

                for(RatingReview rating: ratingReviews){
                    amenities_rating += rating.getAmenities_rating();
                    cleaniness_rating += rating.getCleanliness_rating();
                    manpower_rating += rating.getManpower_rating();
                    curriculum_rating += rating.getCurriculum_rating();
                }
                amenities_rating /= ratingSize;
                cleaniness_rating /= ratingSize;
                manpower_rating /= ratingSize;
                curriculum_rating /= ratingSize;

                cleanlinessRatingBar.setRating((float)(cleaniness_rating));
                amenitiesRatingBar.setRating((float)(amenities_rating));
                manpowerRatingBar.setRating((float)(manpower_rating));
                curriculumRatingBar.setRating((float)(curriculum_rating));


                avgTv.setText( String.format("%.1f", (amenities_rating+cleaniness_rating+manpower_rating+curriculum_rating)/4));



            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.rating_review, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.rating_review_menu:
                RatingReviewActivity.centre_code = kindergarten.getCenter_code();
                startActivity(new Intent(this, RatingReviewActivity.class));
                return true;
            case R.id.share_menu:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
