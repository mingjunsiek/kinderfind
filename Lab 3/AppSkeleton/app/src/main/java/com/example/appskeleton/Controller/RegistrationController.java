package com.example.appskeleton.Controller;

import android.media.Image;

/**
 * This controller handles registering the user to our database
 */
public class RegistrationController {
    /**
     * Default constructor for this controller
     */
    public RegistrationController(){}

    /**
     * This function will insert a new User into our database with the following attributes.
     * When insertion is completed, it will run tutorial().
     * @param email User's email address
     * @param password User's password - Will be hashed for security
     * @param username  User's username
     * @param picture User's profile picture
     */
    public void registerAccount(String email, String password, String username, Image picture){
        //Once user is inserted
        tutorial();
    }

    /**
     * This function will start the tutorial sequence on the Registration Activity
     */
    public void tutorial(){

    }
}
