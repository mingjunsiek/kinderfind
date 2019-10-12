package com.example.kinderfind.models;

public class KindergartenServices {
    String fee_pr;
    String fee_sg;
    String levels_offered;
    String programme_hours;
    Double registration_fee;
    String second_languages_offered;
    String vacancy;

    public KindergartenServices(String fee_pr, String fee_sg, String levels_offered, String programme_hours,
                                Double registration_fee, String second_languages_offered, String vacancy) {
        this.fee_pr = fee_pr;
        this.fee_sg = fee_sg;
        this.levels_offered = levels_offered;
        this.programme_hours = programme_hours;
        this.registration_fee = registration_fee;
        this.second_languages_offered = second_languages_offered;
        this.vacancy = vacancy;
    }

    public String getFee_pr() {
        return fee_pr;
    }

    public String getFee_sg() {
        return fee_sg;
    }

    public String getLevels_offered() {
        return levels_offered;
    }

    public String getProgramme_hours() {
        return programme_hours;
    }

    public Double getRegistration_fee() {
        return registration_fee;
    }

    public String getSecond_languages_offered() {
        return second_languages_offered;
    }

    public String getVacancy() {
        return vacancy;
    }
}
