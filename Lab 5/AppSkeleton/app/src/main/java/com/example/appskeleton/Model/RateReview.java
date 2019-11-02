package com.example.appskeleton.Model;

/**
 * This class implements the RateReview entity with the following attributes:
 *      cleanliness_rating, manpower_rating, curriculum_rating, ameneties_rating,
 *         total_rating, user_id, user_name, review, user_image, time_stamp.
 * @author Ming Jun
 */

public class RateReview {
    private double cleanliness_rating, manpower_rating, curriculum_rating, ameneties_rating,
        total_rating;
    private String user_id, user_name, review, user_image, time_stamp;

    public RateReview(double cleanliness_rating, double manpower_rating, double curriculum_rating,
                      double ameneties_rating, double total_rating, String user_id, String user_name,
                      String review, String user_image, String time_stamp) {
        this.cleanliness_rating = cleanliness_rating;
        this.manpower_rating = manpower_rating;
        this.curriculum_rating = curriculum_rating;
        this.ameneties_rating = ameneties_rating;
        this.total_rating = total_rating;
        this.user_id = user_id;
        this.user_name = user_name;
        this.review = review;
        this.user_image = user_image;
        this.time_stamp = time_stamp;
    }

    /**
     * Below are the Getters and Setters for each attribute.
     */

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

    public double getAmeneties_rating() {
        return ameneties_rating;
    }

    public void setAmeneties_rating(double ameneties_rating) {
        this.ameneties_rating = ameneties_rating;
    }

    public double getTotal_rating() {
        return total_rating;
    }

    public void setTotal_rating(double total_rating) {
        this.total_rating = total_rating;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public String getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }
}
