package com.donation.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class commendation {
    private int commendation_id;
    private String commendation_content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date commendation_time;
    private int commendation_belond;
    private int commendation_person;
    private User user;


    public int getCommendation_person() {
        return commendation_person;
    }

    public void setCommendation_person(int commendation_person) {
        this.commendation_person = commendation_person;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getCommendation_id() {
        return commendation_id;
    }

    public void setCommendation_id(int commendation_id) {
        this.commendation_id = commendation_id;
    }

    public String getCommendation_content() {
        return commendation_content;
    }

    public void setCommendation_content(String commendation_content) {
        this.commendation_content = commendation_content;
    }

    public Date getCommendation_time() {
        return commendation_time;
    }

    public void setCommendation_time(Date commendation_time) {
        this.commendation_time = commendation_time;
    }

    public int getCommendation_belond() {
        return commendation_belond;
    }

    public void setCommendation_belond(int commendation_belond) {
        this.commendation_belond = commendation_belond;
    }
}
