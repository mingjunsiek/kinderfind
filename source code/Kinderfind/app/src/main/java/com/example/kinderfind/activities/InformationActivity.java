package com.example.kinderfind.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.kinderfind.R;
import com.example.kinderfind.adapters.KindergartenServicesAdapter;
import com.example.kinderfind.controller.InformationController;
import com.example.kinderfind.models.Kindergarten;
import com.example.kinderfind.models.KindergartenServices;
import com.example.kinderfind.models.RatingReview;

import java.util.ArrayList;
import java.util.List;

public class InformationActivity extends AppCompatActivity {
    public static Kindergarten kindergarten;
    private TextView centerNameTV, centerAddressTV, centerContactTV, centerEmailTV, centerWebsiteTV, centerCertifiedTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

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

        new InformationController(kindergarten.getCenter_code()).readKindergarten(new InformationController.KindergartenDataStatus() {
            @Override
            public void DataIsLoaded(List<KindergartenServices> kindergartens, List<String> keys) {
                RecyclerView recyclerView = findViewById(R.id.services_recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(InformationActivity.this));
                recyclerView.setHasFixedSize(true);
                KindergartenServicesAdapter adapter = new KindergartenServicesAdapter(kindergartens, keys);
                recyclerView.setAdapter(adapter);
            }
        });

        new InformationController(kindergarten.getCenter_code()).readRatingReview(new InformationController.RatingReviewDataStatus() {
            @Override
            public void DataIsLoaded(List<RatingReview> ratingReviews, List<String> keys) {

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
                //InformationActivity.this.startActivity(new Intent(InformationActivity.this, RatingReviewActivity.class));
                return true;
            case R.id.share_menu:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
