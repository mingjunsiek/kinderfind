package com.example.appskeleton.Controller;

import android.media.Image;

import com.example.appskeleton.Model.User;

/**
 * This controller handles registration of user to our database
 * @author Ming Jun
 */
public class RegistrationController {
    /**
     * Default constructor for this controller
     */
    public RegistrationController(){}

    /**
     * This function will insert a new User into our database with the following attributes.
     * If insertion is successful, startSession() is called to create a session for the current user and returns true.
     * Else, if insertion is unsuccessful, it returns false.
     * @param email User's email address
     * @param password User's password - Will be hashed for security
     * @param username  User's username
     * @param picture User's profile picture
     */
    public boolean registerAccount(String email, String password, String username, Image picture){
        if(true) { //Insertion success
            // Call startSession()
            return true;
        }
        else //Insertion failed
            return false;
    }

    /**
     * This function starts a new session for this current user.
     * It initialize the Session Controller with the user's information.
     * @param user
     */
    public void startSession(User user){
    }

}
