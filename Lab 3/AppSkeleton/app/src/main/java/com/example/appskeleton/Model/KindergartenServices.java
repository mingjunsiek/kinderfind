package com.example.appskeleton.Model;

public class KindergartenServices {
    private String centre_name, fee_sc, fee_pr, levels_offered, programme_hours,
        registration_fee, second_language_offered, vacancy;

    public KindergartenServices(String centre_name, String fee_sc, String fee_pr,
                                String levels_offered, String programme_hours, String registration_fee,
                                String second_language_offered, String vacancy) {
        this.centre_name = centre_name;
        this.fee_sc = fee_sc;
        this.fee_pr = fee_pr;
        this.levels_offered = levels_offered;
        this.programme_hours = programme_hours;
        this.registration_fee = registration_fee;
        this.second_language_offered = second_language_offered;
        this.vacancy = vacancy;
    }

    public String getCentre_name() {
        return centre_name;
    }

    public void setCentre_name(String centre_name) {
        this.centre_name = centre_name;
    }

    public String getFee_sc() {
        return fee_sc;
    }

    public void setFee_sc(String fee_sc) {
        this.fee_sc = fee_sc;
    }

    public String getFee_pr() {
        return fee_pr;
    }

    public void setFee_pr(String fee_pr) {
        this.fee_pr = fee_pr;
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

    public String getRegistration_fee() {
        return registration_fee;
    }

    public void setRegistration_fee(String registration_fee) {
        this.registration_fee = registration_fee;
    }

    public String getSecond_language_offered() {
        return second_language_offered;
    }

    public void setSecond_language_offered(String second_language_offered) {
        this.second_language_offered = second_language_offered;
    }

    public String getVacancy() {
        return vacancy;
    }

    public void setVacancy(String vacancy) {
        this.vacancy = vacancy;
    }
}
