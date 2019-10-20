package com.example.appskeleton.Controller;

import com.example.appskeleton.Model.User;

/**
 * This controller handles validation of user login information with our database
 * @author Ming Jun
 */

public class LoginController {
    /**
     * Stores the User object if user is validated. Passed to session controller to create session.
     */
    private User user;

    /**
     *  This function will sends the email and password to the database to verify if the credentials are
     *  valid.
     *  If it is valid, startSession() is called to create a new session and returns true.
     *  Else, it returns false.
     * @param email User's email address
     * @param password User's password
     * @return boolean
     */
    public boolean authenticateUser(String email, String password){
        if(true)
            return true;
        else
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
