package com.example.kinderfind.models;

public class KindergartenServices {
    String centre_name;
    String fee_pr;
    String fees_sc;
    String levels_offered;
    String programme_hours;
    Double registration_fee;
    String second_languages_offered;
    String vacancy;

    public KindergartenServices(String centre_name, String fee_pr, String fees_sc, String levels_offered,
                                String programme_hours, Double registration_fee, String second_languages_offered, String vacancy) {
        this.centre_name = centre_name;
        this.fee_pr = fee_pr;
        this.fees_sc = fees_sc;
        this.levels_offered = levels_offered;
        this.programme_hours = programme_hours;
        this.registration_fee = registration_fee;
        this.second_languages_offered = second_languages_offered;
        this.vacancy = vacancy;
    }

    public String getCentre_name() {
        return centre_name;
    }

    public void setCentre_name(String centre_name) {
        this.centre_name = centre_name;
    }

    public String getFee_pr() {
        return fee_pr;
    }

    public void setFee_pr(String fee_pr) {
        this.fee_pr = fee_pr;
    }

    public String getFees_sc() {
        return fees_sc;
    }

    public void setFees_sc(String fees_sc) {
        this.fees_sc = fees_sc;
    }

    public String getLevels_offered() {
        return levels_offered;
    }

    public void setLevels_offered(String levels_offered) {
        this.levels_offered = levels_offered;
    }

    public String getProgramme_hours() {
        return programme_hours;
    }

    public void setProgramme_hours(String programme_hours) {
        this.programme_hours = programme_hours;
    }

    public Double getRegistration_fee() {
        return registration_fee;
    }

    public void setRegistration_fee(Double registration_fee) {
        this.registration_fee = registration_fee;
    }

    public String getSecond_languages_offered() {
        return second_languages_offered;
    }

    public void setSecond_languages_offered(String second_languages_offered) {
        this.second_languages_offered = second_languages_offered;
    }

    public String getVacancy() {
        return vacancy;
    }

    public void setVacancy(String vacancy) {
        this.vacancy = vacancy;
    }

    public KindergartenServices(){}


}
