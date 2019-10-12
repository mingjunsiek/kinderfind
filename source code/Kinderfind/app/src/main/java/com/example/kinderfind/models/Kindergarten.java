package com.example.kinderfind.models;

public class Kindergarten {

    private String center_code;
    private String center_address;
    private String centre_contact_no;
    private String centre_email_address;
    private String centre_name;
    private String centre_website;
    private String last_updated;
    private double latitude;
    private double longtitude;
    private String organisation_type;
    private String placeId;
    private String postal_code;
    private String spark_certified;

    public Kindergarten(){

    }

    public Kindergarten(String center_code, String center_address, String centre_contact_no, String centre_email_address, String centre_name,
                        String centre_website, String last_updated, double latitude, double longtitude,
                        String organisation_type, String placeId, String postal_code, String spark_certified) {

        this.center_code = center_code;
        this.center_address = center_address;
        this.centre_contact_no = centre_contact_no;
        this.centre_email_address = centre_email_address;
        this.centre_name = centre_name;
        this.centre_website = centre_website;
        this.last_updated = last_updated;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.organisation_type = organisation_type;
        this.placeId = placeId;
        this.postal_code = postal_code;
        this.spark_certified = spark_certified;

    }

    public String getCenter_code() {
        return center_code;
    }

    public void setCenter_code(String center_code) {
        this.center_code = center_code;
    }

    public String getCenter_address() {
        return center_address;
    }

    public void setCenter_address(String center_address) {
        this.center_address = center_address;
    }

    public String getCentre_contact_no() {
        return centre_contact_no;
    }

    public void setCentre_contact_no(String centre_contact_no) {
        this.centre_contact_no = centre_contact_no;
    }

    public String getCentre_email_address() {
        return centre_email_address;
    }

    public void setCentre_email_address(String centre_email_address) {
        this.centre_email_address = centre_email_address;
    }

    public String getCentre_name() {
        return centre_name;
    }

    public void setCentre_name(String centre_name) {
        this.centre_name = centre_name;
    }

    public String getCentre_website() {
        return centre_website;
    }

    public void setCentre_website(String centre_website) {
        this.centre_website = centre_website;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public String getOrganisation_type() {
        return organisation_type;
    }

    public void setOrganisation_type(String organisation_type) {
        this.organisation_type = organisation_type;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getSpark_certified() {
        return spark_certified;
    }

    public void setSpark_certified(String spark_certified) {
        this.spark_certified = spark_certified;
    }

}