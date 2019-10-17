package com.example.kinderfind.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.kinderfind.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserProfileActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private TextView profileEmail, profileUsername, profileVerifiedTxt;
    private Button logoutButton;
    private ImageView profileImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = auth.getCurrentUser();

        profileEmail = findViewById(R.id.profileEmailTxt);
        profileUsername = findViewById(R.id.profileUsernameTxt);
        profileVerifiedTxt = findViewById(R.id.profileVerifiedTxt);
        profileImageView = findViewById(R.id.profile_image_view);

        profileEmail.setText(user.getEmail());
        profileUsername.setText(user.getDisplayName());

        Glide.with(UserProfileActivity.this)
                .load(user.getPhotoUrl())
                .placeholder(R.mipmap.ic_launcher)
                .apply(RequestOptions.circleCropTransform())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(profileImageView);

        System.out.println("Photo: "+user.getPhotoUrl());
        if(user.isEmailVerified())
            profileVerifiedTxt.setText("True");
        else
            profileVerifiedTxt.setText("False");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.user_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.profile_change_password:
                //Change Password
                return true;
            case R.id.profile_change_image:
                return true;
            case R.id.profile_log_out:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(UserProfileActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
