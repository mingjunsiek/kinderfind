package com.example.appskeleton.Controller;

import com.example.appskeleton.Model.User;

/**
 * This controller handles sending validation email and changing of password for user.
 * @author Ming Jun
 */

public class UserController {

    /**
     * Stores the current user object
     */
    private static User user;

    /**
     * Default constructor for UserController
     */
    public UserController(){}

    /**
     * This function retrieves the user object from session and
     * stores it in this controllers user's object
     */
    public void getUserFromSession(){}

    /**
     * This function sends the verification email to the users email address
     */
    public void sendVerificationEmail(){}

    /**
     * This function change the user's password in the database
     */
    public void changePassword(){}

}
