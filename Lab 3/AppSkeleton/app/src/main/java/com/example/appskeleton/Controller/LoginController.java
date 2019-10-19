package com.example.appskeleton.Controller;

import com.example.appskeleton.Model.User;

/**
 * This controller is used to handle validating user login information with our database
 * @author Ming Jun
 */

public class LoginController {
    /**
     * Stores the User object if user is validated. Passed to session controller to create session.
     */
    private User user;

    /**
     *  This function will sends the email and password to the database to verify if the credentials are
     *  valid. If it is valid, it returns true and start a session for the user. It then brings the user to
     *  the MapActivity
     *  Else, it return false.
     * @param email User's email address
     * @param password User's password
     * @return
     */
    public boolean authenticateUser(String email, String password){

        return false;
    }

    /**
     * This function starts a new session for this current user
     * @param user
     */
    public void startSession(User user){

    }
}
