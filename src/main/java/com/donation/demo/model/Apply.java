package com.donation.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Apply {
    private int apply_id;
    private int apply_person;
    private int demand_id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date apply_time;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date check_time;
    private int plan_num;
    private int real_num;
    private int state_id;
    //关联tb_demand
    private String demand_name;
    //关联tb_state
    private String state;
    //关联tb_user
    private String user_name;
    private String user_apply_nick; //捐助人昵称
    private int user_type;

    private Donation donation;
    private Demand demand;
    private int pic_id;
    //申请捐助时的图片路径
    private String pic_path;

    private User user;
    private String user_email;

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public int getPlan_num() {
        return plan_num;
    }

    public void setPlan_num(int plan_num) {
        this.plan_num = plan_num;
    }

    public int getReal_num() {
        return real_num;
    }

    public void setReal_num(int real_num) {
        this.real_num = real_num;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPic_path() {
        return pic_path;
    }

    public void setPic_path(String pic_path) {
        this.pic_path = pic_path;
    }

    public int getPic_id() {
        return pic_id;
    }

    public void setPic_id(int pic_id) {
        this.pic_id = pic_id;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public String getUser_apply_nick() {
        return user_apply_nick;
    }

    public void setUser_apply_nick(String user_apply_nick) {
        this.user_apply_nick = user_apply_nick;
    }

    public Demand getDemand() {
        return demand;
    }

    public void setDemand(Demand demand) {
        this.demand = demand;
    }



    public Donation getDonation() {
        return donation;
    }

    public void setDonation(Donation donation) {
        this.donation = donation;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDemand_name() {
        return demand_name;
    }

    public void setDemand_name(String demand_name) {
        this.demand_name = demand_name;
    }

    public int getApply_id() {
        return apply_id;
    }

    public void setApply_id(int apply_id) {
        this.apply_id = apply_id;
    }

    public int getApply_person() {
        return apply_person;
    }

    public void setApply_person(int apply_person) {
        this.apply_person = apply_person;
    }

    public int getDemand_id() {
        return demand_id;
    }

    public void setDemand_id(int demand_id) {
        this.demand_id = demand_id;
    }

    public Date getApply_time() {
        return apply_time;
    }

    public void setApply_time(Date apply_time) {
        this.apply_time = apply_time;
    }

    public Date getCheck_time() {
        return check_time;
    }

    public void setCheck_time(Date check_time) {
        this.check_time = check_time;
    }





    public int getState_id() {
        return state_id;
    }

    public void setState_id(int state_id) {
        this.state_id = state_id;
    }


}
