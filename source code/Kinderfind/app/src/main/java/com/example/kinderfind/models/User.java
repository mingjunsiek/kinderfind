package com.example.kinderfind.models;

public class User {
    private String userID;
    private String username;
    private String email;
    private String userImageUrl;
    private boolean verified;

    public User(String userID, String username, String email, String userImageUrl, boolean verified) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.userImageUrl = userImageUrl;
        this.verified = verified;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
