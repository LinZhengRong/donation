package com.donation.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class announcement {
    private int announcement_id;
    private String announcement_content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date announcement_time;
    private Date announcement_modify;
    private String announcement_title;
    private int announcement_publisher;
    //关联tb_user
    private String user_name;
    private String user_nick;

    public String getUser_nick() {
        return user_nick;
    }

    public void setUser_nick(String user_nick) {
        this.user_nick = user_nick;
    }

    public int getAnnouncement_id() {
        return announcement_id;
    }

    public void setAnnouncement_id(int announcement_id) {
        this.announcement_id = announcement_id;
    }

    public String getAnnouncement_content() {
        return announcement_content;
    }

    public void setAnnouncement_content(String announcement_content) {
        this.announcement_content = announcement_content;
    }

    public Date getAnnouncement_time() {
        return announcement_time;
    }

    public void setAnnouncement_time(Date announcement_time) {
        this.announcement_time = announcement_time;
    }

    public Date getAnnouncement_modify() {
        return announcement_modify;
    }

    public void setAnnouncement_modify(Date announcement_modify) {
        this.announcement_modify = announcement_modify;
    }

    @Override
    public String toString() {
        return "announcement{" +
                "announcement_id=" + announcement_id +
                ", announcement_content='" + announcement_content + '\'' +
                ", announcement_time=" + announcement_time +
                ", announcement_modify=" + announcement_modify +
                '}';
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getAnnouncement_title() {
        return announcement_title;
    }

    public void setAnnouncement_title(String announcement_title) {
        this.announcement_title = announcement_title;
    }

    public int getAnnouncement_publisher() {
        return announcement_publisher;
    }

    public void setAnnouncement_publisher(int announcement_publisher) {
        this.announcement_publisher = announcement_publisher;
    }
}
