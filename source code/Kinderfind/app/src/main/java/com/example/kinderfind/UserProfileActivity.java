package com.example.kinderfind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserProfileActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private TextView profileEmail, profileUsername, profileVerifiedTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = auth.getCurrentUser();

        profileEmail = findViewById(R.id.profileEmailTxt);
        profileUsername = findViewById(R.id.profileUsernameTxt);
        profileVerifiedTxt = findViewById(R.id.profileVerifiedTxt);

        profileEmail.setText(user.getEmail());
        profileUsername.setText(user.getDisplayName());
        if(user.isEmailVerified())
            profileVerifiedTxt.setText("True");
        else
            profileVerifiedTxt.setText("False");


    }
}
