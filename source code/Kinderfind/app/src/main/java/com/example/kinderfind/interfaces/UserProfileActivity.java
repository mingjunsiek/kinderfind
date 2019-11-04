package com.example.kinderfind.interfaces;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.kinderfind.R;
import com.example.kinderfind.utils.InternetReceiver;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;

public class UserProfileActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private TextView profileEmail, profileUsername, profileVerifiedTxt;
    private Button verificationBtn;
    private ImageView profileImageView;
    private static final int READ_REQUEST_CODE = 42;
    private Uri imageUri;
    private FirebaseUser user;
    private StorageReference mStorageRef;
    private InternetReceiver internetReceiver;
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_interface);

        // Register InternetReceiver
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        intentFilter = new IntentFilter();
        intentFilter.addAction(CONNECTIVITY_ACTION);
        internetReceiver = new InternetReceiver(cm);

        if(!internetReceiver.checkForInternet())
            Toast.makeText(this, R.string.no_internet, Toast.LENGTH_LONG).show();

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        mStorageRef = FirebaseStorage.getInstance().getReference("profile_pic");

        profileEmail = findViewById(R.id.profileEmailTxt);
        profileUsername = findViewById(R.id.profileUsernameTxt);
        profileVerifiedTxt = findViewById(R.id.profileVerifiedTxt);
        profileImageView = findViewById(R.id.profile_image_view);
        verificationBtn = findViewById(R.id.profile_send_verification);

        profileEmail.setText(user.getEmail());
        profileUsername.setText(user.getDisplayName());

        mStorageRef.child(user.getUid()).getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Glide.with(UserProfileActivity.this)
                                .load(uri)
                                .placeholder(R.mipmap.ic_launcher)
                                .apply(RequestOptions.circleCropTransform())
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(profileImageView);
                        Log.d("Get Profile Pic", "Profile Pic Retrieval Success");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Get Profile Pic", "Profile Pic Retrieval Failed");
            }
        });

        if(user.isEmailVerified())
            profileVerifiedTxt.setText("True");
        else {
            profileVerifiedTxt.setText("False");
            verificationBtn.setVisibility(View.VISIBLE);
        }

        verificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(UserProfileActivity.this, "Verification Email Sent. Please check your email."
                                    , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

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

                uploadFile();
            }
        }
    }

    private void uploadFile(){
        if(imageUri != null){
            StorageReference fileReference = mStorageRef.child(user.getUid());

            fileReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(UserProfileActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                            Log.d("Profile update", "User profile updated.");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(UserProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        else{
            Toast.makeText(this, "No File Selected", Toast.LENGTH_SHORT).show();
        }
    }

}
