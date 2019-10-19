package com.example.appskeleton.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.appskeleton.R;

/**
 * This activity allows user to view their information.
 * If they have not validate their email, they can send the verification email here.
 * They can change their password here as well.
 */
public class UserProfileActivity extends AppCompatActivity {

    /**
     * This function will create the text to display user's information.
     * The send verification button is created here.
     * The change user's password button is created here
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }
    
    public void tapOnSendVerificationEmail(){}

    public void submiteChangePassword(){}
}
