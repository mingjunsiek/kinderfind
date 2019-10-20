package com.example.appskeleton.Controller;

import com.example.appskeleton.Model.User;

/**
 * This controller handles the session of the current user.
 * @author Ming Jun
 */
public class SessionController {

    /**
     * Stores the User object of the current user.
     */
    private User currentUser;

    /**
     * Stores a boolean depending if the user is logged in or not.
     */
    private boolean isLoggedIn;

    /**
     * Default constructor to initialise the SessionController.
     * @param user User object is initialized to this Session Controller.
     */
    public SessionController(User user){
        currentUser = user;
    }

    /**
     * This function returns the user object.
     * @return returns currentUser.
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * This function return isLoggedIn.
     * @return returns isLoggedIn.
     */
    public boolean checkLogin(){
        return isLoggedIn;
    }
}
