package com.example.kinderfind.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kinderfind.R;
import com.example.kinderfind.adapters.KindergartenServicesAdapter;
import com.example.kinderfind.adapters.RatingReviewAdapter;
import com.example.kinderfind.controller.InformationController;
import com.example.kinderfind.models.Kindergarten;
import com.example.kinderfind.models.KindergartenServices;
import com.example.kinderfind.models.RatingReview;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class InformationActivity extends AppCompatActivity {
    public static Kindergarten kindergarten;
    private TextView centerNameTV, centerAddressTV, centerContactTV, centerEmailTV, centerWebsiteTV, centerCertifiedTV, reviewTV;
    private CardView reviewCardView;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private InformationController informationController;
    private RecyclerView reviewRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        setTitle(kindergarten.getCentre_name());
        centerNameTV = findViewById(R.id.center_name);
        centerAddressTV = findViewById(R.id.center_address);
        centerContactTV = findViewById(R.id.center_contact);
        centerEmailTV = findViewById(R.id.center_email);
        centerWebsiteTV = findViewById(R.id.center_website);
        centerCertifiedTV = findViewById(R.id.center_certified);

        reviewTV = findViewById(R.id.info_review_text_view);
        reviewCardView = findViewById(R.id.info_review_card_view);

        centerNameTV.setText(kindergarten.getCentre_name());
        centerAddressTV.setText(kindergarten.getCenter_address());
        centerContactTV.setText(kindergarten.getCentre_contact_no());
        centerEmailTV.setText(kindergarten.getCentre_email_address());
        centerWebsiteTV.setText(kindergarten.getCentre_website());
        centerCertifiedTV.setText(kindergarten.getSpark_certified());

        informationController = new InformationController(kindergarten.getCenter_code());

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
                if(ratingReviews.size() > 0) {
                    reviewTV.setText("REVIEWS");
                    reviewCardView.setVisibility(View.VISIBLE);
                    RecyclerView recyclerView = findViewById(R.id.review_recycler_view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(InformationActivity.this));
                    recyclerView.setHasFixedSize(true);
                    RatingReviewAdapter adapter = new RatingReviewAdapter(ratingReviews, keys, InformationActivity.this);
                    recyclerView.setAdapter(adapter);
                }
                else{
                    reviewTV.setText("No Reviews Yet");
                    reviewCardView.setVisibility(View.GONE);
                }
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
    protected void onResume() {
        super.onResume();


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.rating_review_menu:
                if(!user.isEmailVerified()) {
                    Toast.makeText(this, getText(R.string.toast_verified),
                            Toast.LENGTH_LONG).show();
                    return true;
                }
                else {
                    RatingReviewActivity.centre_code = kindergarten.getCenter_code();
                    startActivity(new Intent(this, RatingReviewActivity.class));
                    //InformationActivity.this.startActivity(new Intent(InformationActivity.this, RatingReviewActivity.class));
                    return true;
                }
            case R.id.share_menu:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
