package com.example.appskeleton.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.appskeleton.Model.User;
import com.example.appskeleton.R;

/**
 * When user first start up the app, the first view they will see is the Login view.
 * Here, users can log in to their account by entering their email and password.
 * If not, they can tap on the register button to register an account.
 * @author Ming Jun
 */

public class LoginActivity extends AppCompatActivity {

    /**
     * This function will create the fields for email and password.
     * Login and Registration button will be created here as well.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /**
     * This method passes the user's email and password to the LoginController to authenticate the user
     * @param email refers to user's email address
     * @param password refers to user's password
     */
    public void submitLogin(String email, String password){

    }

    /**
     * This method ensures that the fields are not empty when user tap on the login button.
     * If it is empty, showError() will be display an error
     *
     */
    public void validateField(){

    }

    /**
     *  Display an error with informative information
     */
    public void showError(){

    }

}
