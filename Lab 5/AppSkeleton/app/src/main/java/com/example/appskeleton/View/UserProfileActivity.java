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
     * If user is not verified, the Send Verification button will be displayed
     * The change user's password button is created here
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }

    /**
     * This function will call the controller's sendVerificationEmail()
     */
    public void tapOnSendVerificationEmail(){}

    /**
     * This function will call the controller's changePassword()
     */
    public void submitChangePassword(){}

    /**
     * This function will be used to display the result based on the message parameter passed to it.
     * @param message The message to use in the Toast message.
     */
    public void displayMessage(String message){}
}
