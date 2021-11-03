package com.donation.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Picture {
    private int pic_id;
    private String pic_path;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date pic_upload_time;

    public int getPic_id() {
        return pic_id;
    }

    public void setPic_id(int pic_id) {
        this.pic_id = pic_id;
    }

    public String getPic_path() {
        return pic_path;
    }

    public void setPic_path(String pic_path) {
        this.pic_path = pic_path;
    }



    public Date getPic_upload_time() {
        return pic_upload_time;
    }

    public void setPic_upload_time(Date pic_upload_time) {
        this.pic_upload_time = pic_upload_time;
    }
}
