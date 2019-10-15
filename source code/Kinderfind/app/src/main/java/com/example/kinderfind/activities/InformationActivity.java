package com.example.kinderfind.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.kinderfind.R;
import com.example.kinderfind.adapters.KindergartenServicesAdapter;
import com.example.kinderfind.controller.InformationController;
import com.example.kinderfind.models.Kindergarten;
import com.example.kinderfind.models.KindergartenServices;

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

        new InformationController(kindergarten.getCenter_code()).readKindergarten(new InformationController.DataStatus() {
            @Override
            public void DataIsLoaded(List<KindergartenServices> kindergartens, List<String> keys) {
                RecyclerView recyclerView = findViewById(R.id.services_recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(InformationActivity.this));
                recyclerView.setHasFixedSize(true);
                KindergartenServicesAdapter adapter = new KindergartenServicesAdapter(kindergartens, keys);
                recyclerView.setAdapter(adapter);
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
