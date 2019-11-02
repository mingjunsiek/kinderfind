package com.example.kinderfind.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.kinderfind.R;
import com.example.kinderfind.adapters.LocalStorage;
import com.example.kinderfind.model.DbController;
import com.example.kinderfind.utils.InternetReceiver;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;

public class SplashScreenActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private InternetReceiver internetReceiver;
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();

        // Register InternetReceiver
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        intentFilter = new IntentFilter();
        intentFilter.addAction(CONNECTIVITY_ACTION);
        internetReceiver = new InternetReceiver(cm);

    }

    public void onStart() {
        super.onStart();

        if(!internetReceiver.checkForInternet()) {
            LocalStorage localStorage = new LocalStorage(getApplicationContext());
            if (localStorage.getFromSharedPreferences() == null || localStorage.getFromSharedPreferences().size() == 0) {
                //if no data load data

                Log.d("splashscreen", "THERE ARE NO DATA");
                DbController dbController = new DbController();
                dbController.readDataFromKindergarten(getApplicationContext(), new DbController.FirebaseSuccessListener() {

                    @Override
                    public void onKindergartenDataCompleted(boolean isDataCompleted) {

                        // Check if user is signed in (non-null) and update UI accordingly.
                        FirebaseUser user = auth.getCurrentUser();
                        if (user != null) {
                            Intent intent = new Intent(SplashScreenActivity.this, MapsActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Log.d("splashscreen", "onKindergartenDataCompleted: data is loaded but user is null");
                            Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });

            } else {
                //data is already loaded
                // Check if user is signed in

                Log.d("splashscreen", "DATA ALREADY EXIST");
                FirebaseUser user = auth.getCurrentUser();
                if (user != null) {
                    Log.d("splashscreen", "DATA ALREADY EXIST AND THERE IS USER");
                    Intent intent = new Intent(SplashScreenActivity.this, MapsActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Log.d("splashscreen", "DATA ALREADY EXIST BUT THERE IS NO USER");
                    Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }
        else{
            Log.d("splashscreen", "NO INTERNET PROCEED TO LOGIN");
            Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
