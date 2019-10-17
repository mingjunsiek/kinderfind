package com.example.kinderfind.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.kinderfind.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class UserProfileActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private TextView profileEmail, profileUsername, profileVerifiedTxt;
    private ImageView profileImageView;
    private static final int READ_REQUEST_CODE = 42;
    private Uri imageUri;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

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
        Intent intent;
        switch(item.getItemId()){
            case R.id.profile_change_password:
                intent = new Intent(UserProfileActivity.this, PasswordActivity.class);
                startActivity(intent);
                return true;

            case R.id.profile_change_image:
                openFileChooser();
                return true;

            case R.id.profile_log_out:
                FirebaseAuth.getInstance().signOut();
                intent = new Intent(UserProfileActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void openFileChooser(){

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            imageUri = null;
            if (data != null) {
                imageUri = data.getData();

                Glide.with(UserProfileActivity.this)
                        .load(imageUri)
                        .apply(RequestOptions.circleCropTransform())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(profileImageView);


                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setPhotoUri(imageUri)
                        .build();

                user.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d("Profile update", "User profile updated.");
                                }
                            }
                        });
            }
        }
    }

}
