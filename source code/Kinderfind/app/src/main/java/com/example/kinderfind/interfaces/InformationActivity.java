package com.example.kinderfind.interfaces;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
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
import com.example.kinderfind.model.informationModel;
import com.example.kinderfind.entities.Kindergarten;
import com.example.kinderfind.entities.KindergartenServices;
import com.example.kinderfind.entities.RatingReview;
import com.example.kinderfind.utils.InternetReceiver;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;

public class InformationActivity extends AppCompatActivity implements OnMapReadyCallback {
    public static Kindergarten kindergarten;
    private TextView centerNameTV, centerAddressTV, centerContactTV, centerEmailTV, centerWebsiteTV, centerCertifiedTV, reviewTV, ratingTV, avgTv;
    private CardView reviewCardView, ratingCardView;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private informationModel informationModel;
    private InternetReceiver internetReceiver;
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_interface);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        System.out.println("CHECK USER: "+user);
        // Register InternetReceiver
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        intentFilter = new IntentFilter();
        intentFilter.addAction(CONNECTIVITY_ACTION);
        internetReceiver = new InternetReceiver(cm);

        if(!internetReceiver.checkForInternet())
            Toast.makeText(this, R.string.no_internet, Toast.LENGTH_LONG).show();

        final MaterialRatingBar cleanlinessRatingBar = findViewById(R.id.info_cleaniness);
        final MaterialRatingBar manpowerRatingBar = findViewById(R.id.info_manpower);
        final MaterialRatingBar curriculumRatingBar = findViewById(R.id.info_curriculum);
        final MaterialRatingBar amenitiesRatingBar = findViewById(R.id.info_facilities);
        avgTv = findViewById(R.id.info_avg);
        setTitle(kindergarten.getCentre_name());
        centerNameTV = findViewById(R.id.center_name);
        centerAddressTV = findViewById(R.id.center_address);
        centerContactTV = findViewById(R.id.center_contact);
        centerEmailTV = findViewById(R.id.center_email);
        centerWebsiteTV = findViewById(R.id.center_website);
        centerCertifiedTV = findViewById(R.id.center_certified);
        avgTv = findViewById(R.id.info_avg);
        reviewTV = findViewById(R.id.info_review_text_view);
        reviewCardView = findViewById(R.id.info_review_card_view);
        ratingTV = findViewById(R.id.info_ratings_text_view);
        ratingCardView = findViewById(R.id.info_ratings_card_view);
        centerNameTV.setText(kindergarten.getCentre_name());
        centerAddressTV.setText(kindergarten.getCenter_address());
        centerContactTV.setText(kindergarten.getCentre_contact_no());
        centerEmailTV.setText(kindergarten.getCentre_email_address());
        centerWebsiteTV.setText(kindergarten.getCentre_website());
        centerCertifiedTV.setText(kindergarten.getSpark_certified());

        informationModel = new informationModel(kindergarten.getCenter_code());

        informationModel.readKindergarten(new informationModel.KindergartenDataStatus() {
            @Override
            public void DataIsLoaded(List<KindergartenServices> kindergartens, List<String> keys) {
                RecyclerView recyclerView = findViewById(R.id.services_recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(InformationActivity.this));
                recyclerView.setHasFixedSize(true);
                KindergartenServicesAdapter adapter = new KindergartenServicesAdapter(kindergartens, keys);
                recyclerView.setAdapter(adapter);
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.info_map);
        mapFragment.getMapAsync(this);

        informationModel.readRatingReview(new informationModel.RatingReviewDataStatus() {
            @Override
            public void DataIsLoaded(List<RatingReview> ratingReviews, List<String> keys) {

                if(ratingReviews.size() > 0) {
                    reviewTV.setText("REVIEWS");
                    reviewCardView.setVisibility(View.VISIBLE);

                    ratingTV.setText("RATINGS");
                    ratingCardView.setVisibility(View.VISIBLE);

                    double amenities_rating = 0;
                    double cleaniness_rating = 0;
                    double manpower_rating = 0;
                    double curriculum_rating = 0;
                    int ratingSize = ratingReviews.size();

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
                    double average = (amenities_rating+cleaniness_rating+manpower_rating+curriculum_rating)/4;

                    avgTv.setText( String.format("%.1f", average));

                    RecyclerView recyclerView = findViewById(R.id.review_recycler_view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(InformationActivity.this));
                    recyclerView.setHasFixedSize(true);
                    RatingReviewAdapter adapter = new RatingReviewAdapter(ratingReviews, keys, InformationActivity.this);
                    recyclerView.setAdapter(adapter);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(user != null) {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.rating_review, menu);
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(internetReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(internetReceiver);
    }

    @Override
    public void onBackPressed() {
        if(user != null)
            super.onBackPressed();
        else
            startActivity(new Intent(InformationActivity.this, LoginActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                if(user == null)
                    startActivity(new Intent(InformationActivity.this, LoginActivity.class));
                else
                    startActivity(new Intent(InformationActivity.this, MapsActivity.class));
                return true;
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
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "https://kinderfind.page.link/" + kindergarten.getPlaceId());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(kindergarten.getLatitude(), kindergarten.getLongtitude()))      // Sets the center of the map to Mountain View
                .zoom(15)                   // Sets the zoom
                .bearing(0)                // Sets the orientation of the camera to east
                .tilt(0)                   // Sets the tilt of the camera to 30 degrees
                .build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.setTrafficEnabled(false);
        googleMap.setIndoorEnabled(false);
        googleMap.setBuildingsEnabled(true);

        BitmapDrawable bitmapdraw=(BitmapDrawable) ContextCompat.getDrawable(getApplicationContext(), R.drawable.kmarker);
        Bitmap b=bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 59, 80, false);

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(kindergarten.getLatitude(), kindergarten.getLongtitude()))
                .title(kindergarten.getCentre_name())
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));

    }
}
