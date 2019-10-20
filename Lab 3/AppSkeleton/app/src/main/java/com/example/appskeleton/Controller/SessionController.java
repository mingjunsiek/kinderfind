package com.example.appskeleton.Controller;

import com.example.appskeleton.Model.User;

public class SessionController {
    private User currentUser;
    private boolean isLoggedIn;

    public SessionController(User user){
        currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }
    public boolean checkLogin(){
        return isLoggedIn;
    }
}
