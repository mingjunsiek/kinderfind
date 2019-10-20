package com.example.appskeleton.Model;

/**
 * This class implements the Kindergarten entity with the following attributes:
 *      entre_code, centre_address, centre_contact_no,
 *      centre_email_address, centre_name, centre_website, last_updated,
 *      organization_type, placeid, postal_code, sparkCertified, latitude, longtitude.
 * @author Ming Jun
 */
public class Kindergarten {
    private String centre_code, centre_address, centre_contact_no,
        centre_email_address, centre_name, centre_website, last_updated,
        organization_type, placeid, postal_code, sparkCertified;
    private double latitude, longtitude;

    public Kindergarten(String centre_code, String centre_address, String centre_contact_no,
                        String centre_email_address, String centre_name, String centre_website,
                        String last_updated, String organization_type, String placeid,
                        String postal_code, String sparkCertified, double latitude, double longtitude) {
        this.centre_code = centre_code;
        this.centre_address = centre_address;
        this.centre_contact_no = centre_contact_no;
        this.centre_email_address = centre_email_address;
        this.centre_name = centre_name;
        this.centre_website = centre_website;
        this.last_updated = last_updated;
        this.organization_type = organization_type;
        this.placeid = placeid;
        this.postal_code = postal_code;
        this.sparkCertified = sparkCertified;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    /**
     * Below are the Getters and Setters for each attribute.
     */

    public String getCentre_code() {
        return centre_code;
    }

    public void setCentre_code(String centre_code) {
        this.centre_code = centre_code;
    }

    public String getCentre_address() {
        return centre_address;
    }

    public void setCentre_address(String centre_address) {
        this.centre_address = centre_address;
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

    public String getOrganization_type() {
        return organization_type;
    }

    public void setOrganization_type(String organization_type) {
        this.organization_type = organization_type;
    }

    public String getPlaceid() {
        return placeid;
    }

    public void setPlaceid(String placeid) {
        this.placeid = placeid;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getSparkCertified() {
        return sparkCertified;
    }

    public void setSparkCertified(String sparkCertified) {
        this.sparkCertified = sparkCertified;
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
}
