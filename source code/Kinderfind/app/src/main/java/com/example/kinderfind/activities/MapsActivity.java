package com.example.kinderfind.activities;


import com.example.kinderfind.adapters.DbAdapter;
import com.example.kinderfind.adapters.FirebaseSuccessListener;
import com.example.kinderfind.adapters.KindergartenAdapter;
import com.example.kinderfind.adapters.LocalStorage;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kinderfind.models.Kindergarten;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.location.Location;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kinderfind.R;
import com.example.kinderfind.utils.UnitConversionUtil;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.model.Marker;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.auth.FirebaseAuth;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.google.firebase.auth.FirebaseUser;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private boolean mLocationPermissionGranted;
    private static final String TAG = MapsActivity.class.getSimpleName();
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";
    private static final int DEFAULT_ZOOM = 14;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private final LatLng mDefaultLocation = new LatLng(1.3553794, 103.8677444);
    private LocalStorage localStorage;
    private ImageButton profileBtn;

    private ArrayList<Kindergarten> kindergartenArrayList = new ArrayList<>();
    private RecyclerView recyclerView;

    private BottomSheetBehavior bottomSheetBehavior;
    private TextView title;
    private KindergartenAdapter kindergartenAdapter;
    
    private CameraPosition mCameraPosition;
    private Location mCurrentLocation;

    // The entry point to the Fused Location Provider.
    private FusedLocationProviderClient mFusedLocationProviderClient;

    // The geographical location where the device is currently located. That is, the last-known
    // location retrieved by the Fused Location Provider.
    private Location mLastKnownLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //Scroll View
        View scrollView = findViewById(R.id.kindergarten_sv);
        bottomSheetBehavior = BottomSheetBehavior.from(scrollView);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                if (i == BottomSheetBehavior.STATE_HIDDEN)
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });

        startAsyncTask();
        //Kindergarten Recycler View
        title = findViewById(R.id.title_tv);
        recyclerView = findViewById(R.id.kindergarten_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //kindergartenAdapter = new KindergartenAdapter(kindergartenArrayList, this);
        //recyclerView.setAdapter(kindergartenAdapter);

        //declare constructors
        profileBtn = findViewById(R.id.mainProfileBtn);
        localStorage = new LocalStorage(getApplicationContext());

        FloatingSearchView pSearchView = findViewById(R.id.floating_search_view);

        if (savedInstanceState != null) {
            mCurrentLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            mCameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }

        // Construct a FusedLocationProviderClient.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        View locationButton = ((View) mapFragment.getView().findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
        RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
        rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0);
        rlp.setMargins(0, 200, 50, 0);

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MapsActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                Log.d(TAG, "onClick: profilebtn");
            }
        });

        // Search bar query
        pSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {

                //kindergartenAdapter.getFilter().filter(newQuery);

                if (kindergartenAdapter.getItemCount() == 0) {
                    bottomSheetBehavior.setPeekHeight(UnitConversionUtil.convertDpToPx(100));

                    title.setText("No Results Available");
                } else {

                    Log.d(TAG, "onSearchTextChanged: have something");
                    bottomSheetBehavior.setPeekHeight(UnitConversionUtil.convertDpToPx(300));

                    title.setText("Hawker Centres Nearby");
                }
            }
        });
    }

    public void startAsyncTask(){
        loadData task = new loadData(this);
        task.execute();
    }

    private static class loadData extends AsyncTask<String, Void, String>{
        private WeakReference<MapsActivity> activityWeakReference;

        loadData(MapsActivity activity){
            activityWeakReference = new WeakReference<MapsActivity>(activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            MapsActivity activity = activityWeakReference.get();
            if(activity == null || activity.isFinishing()){
                return;
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            MapsActivity activity = activityWeakReference.get();
            if(activity == null || activity.isFinishing()){
                return "Null or Empty";
            }
            System.out.println("doInBackground");
            return "Loaded Data";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            MapsActivity activity = activityWeakReference.get();
            if(activity == null || activity.isFinishing()){
                return;
            }
            System.out.println("onPostExecute");
            activity.kindergartenArrayList = activity.localStorage.getFromSharedPreferences();
            activity.kindergartenAdapter = new KindergartenAdapter(activity.kindergartenArrayList, activity);
            activity.recyclerView.setAdapter(activity.kindergartenAdapter);
        }
    }

    private void getLocationPermission() {

        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }

    public void onMapReady(GoogleMap map) {
        System.out.println("IN GOOGLE MAP");
        mMap = map;
        getMarkers();

        // Prompt the user for permission.
        getLocationPermission();
        // Get the current location of the device and set the position of the map.
        getDeviceLocation();
        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();
    }

    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);

            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            System.out.println("getDeviceLocation");
            if (mLocationPermissionGranted) {
                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = task.getResult();
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(mLastKnownLocation.getLatitude(),
                                            mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                        } else {
                            Log.d(TAG, "Current location is null. Using defaults.");
                            Log.e(TAG, "Exception: %s", task.getException());
                            mMap.moveCamera(CameraUpdateFactory
                                    .newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    public void getMarkers() {
        if (mMap == null) {
            return;
        }
        try {

            kindergartenArrayList = localStorage.getFromSharedPreferences();
            if (kindergartenArrayList.size() != 0) {
                for (Kindergarten k : kindergartenArrayList) {
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(k.getLatitude(), k.getLongtitude()))
                            .title(k.getCentre_name())).setTag(k.getCenter_code());
                    // Set a listener for marker click.
                    mMap.setOnMarkerClickListener((GoogleMap.OnMarkerClickListener) this);
                }
            } else
                Log.d(TAG, "getMarkers: empty kindergartenArraylist");
        } catch (SecurityException e) {
            Log.d(TAG, "getMarkers: unable to store markers");
        }
    }
    /**
     * Called when the user clicks a marker.
     */
    @Override
    public boolean onMarkerClick(final Marker marker) {

        // Retrieve the centrecode from the marker.
        String centreCode = (String) marker.getTag();

        if (centreCode != null) {
            Toast.makeText(this, "the centre code is " + centreCode,
                    Toast.LENGTH_SHORT).show();
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mMap != null) {
            outState.putParcelable(KEY_CAMERA_POSITION, mMap.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, mLastKnownLocation);
            super.onSaveInstanceState(outState);
        }
    }

}
