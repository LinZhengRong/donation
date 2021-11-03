package com.donation.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Feedback {
    private int feedback_id;
    private String feedback_content;
    private int feedback_senderID;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date feedback_time;



    //关联用户表
    private User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(int feedback_id) {
        this.feedback_id = feedback_id;
    }

    public String getFeedback_content() {
        return feedback_content;
    }

    public void setFeedback_content(String feedback_content) {
        this.feedback_content = feedback_content;
    }

    public int getFeedback_senderID() {
        return feedback_senderID;
    }

    public void setFeedback_senderID(int feedback_senderID) {
        this.feedback_senderID = feedback_senderID;
    }

    public Date getFeedback_time() {
        return feedback_time;
    }

    public void setFeedback_time(Date feedback_time) {
        this.feedback_time = feedback_time;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "feedback_id=" + feedback_id +
                ", feebback_content='" + feedback_content + '\'' +
                ", feedback_senderID=" + feedback_senderID +
                ", feedback_time=" + feedback_time +
                '}';
    }
}
