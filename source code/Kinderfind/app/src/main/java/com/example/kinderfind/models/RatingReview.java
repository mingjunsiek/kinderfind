package com.example.kinderfind.models;

import java.util.Map;

public class RatingReview {
    private double cleanliness_rating;
    private double manpower_rating;
    private double curriculum_rating;
    private double amenities_rating;
    private double total_rating;
    private String user_id;
    private String username;
    private String review;
    //private String kindergarten_id;
    private long timestamp;

    public RatingReview(){
        
    }

    public RatingReview(double cleanliness_rating, double manpower_rating, double curriculum_rating,
                        double amenities_rating, double total_rating, String user_id, String username, String review, long timestamp) {
        this.cleanliness_rating = cleanliness_rating;
        this.manpower_rating = manpower_rating;
        this.curriculum_rating = curriculum_rating;
        this.amenities_rating = amenities_rating;
        this.total_rating = total_rating;
        this.user_id = user_id;
        this.username = username;
        this.review = review;
        this.timestamp = timestamp;
    }

    public double getCleanliness_rating() {
        return cleanliness_rating;
    }

    public void setCleanliness_rating(double cleanliness_rating) {
        this.cleanliness_rating = cleanliness_rating;
    }

    public double getManpower_rating() {
        return manpower_rating;
    }

    public void setManpower_rating(double manpower_rating) {
        this.manpower_rating = manpower_rating;
    }

    public double getCurriculum_rating() {
        return curriculum_rating;
    }

    public void setCurriculum_rating(double curriculum_rating) {
        this.curriculum_rating = curriculum_rating;
    }

    public double getAmenities_rating() {
        return amenities_rating;
    }

    public void setAmenities_rating(double amenities_rating) {
        this.amenities_rating = amenities_rating;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getTotal_rating() {
        return total_rating;
    }

    public void setTotal_rating(double total_rating) {
        this.total_rating = total_rating;
    }
}
