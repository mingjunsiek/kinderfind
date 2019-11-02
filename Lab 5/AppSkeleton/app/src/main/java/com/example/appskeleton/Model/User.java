package com.example.appskeleton.Model;

import android.graphics.Picture;

/**
 * This class implements the User entity with the following attributes:
 *      email, username, password, profile_picture, verified_user.
 * @author Ming Jun
 */

public class User {
    private String email, username, password;
    private Picture profile_picture;
    private boolean verified_user;

    public User(String email, String username, String password, Picture profile_picture, boolean verified_user) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.profile_picture = profile_picture;
        this.verified_user = verified_user;
    }

    /**
     * Below are the Getters and Setters for each attribute.
     */

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Picture getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(Picture profile_picture) {
        this.profile_picture = profile_picture;
    }

    public boolean isVerified_user() {
        return verified_user;
    }

    public void setVerified_user(boolean verified_user) {
        this.verified_user = verified_user;
    }
}
