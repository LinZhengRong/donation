package com.donation.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Thank {
    private int thank_id;
    private String thank_content;
    private int donation_id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date thank_time;

    public int getThank_id() {
        return thank_id;
    }

    public void setThank_id(int thank_id) {
        this.thank_id = thank_id;
    }

    public String getThank_content() {
        return thank_content;
    }

    public void setThank_content(String thank_content) {
        this.thank_content = thank_content;
    }

    public int getDonation_id() {
        return donation_id;
    }

    public void setDonation_id(int donation_id) {
        this.donation_id = donation_id;
    }

    public Date getThank_time() {
        return thank_time;
    }

    public void setThank_time(Date thank_time) {
        this.thank_time = thank_time;
    }
}
