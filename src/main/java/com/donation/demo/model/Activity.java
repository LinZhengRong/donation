package com.donation.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
public class Activity {
    private int activity_id;
    private int activity_publisher;
    private String activity_title;
    private int activity_team_num;
    private int activity_type;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date activity_start_time;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date activity_end_time;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date activity_publish_time;
    private int activity_number;
    private int activity_real_number;
    private int activity_area;
    private String activity_content;
    private int activity_update_by;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date activity_update_time;
    //关联用户表
    private String user_name;
    //关联地址表
    private String address_name;
    private Address address;
    //关联活动类型表
    private String type_name;
    //临时存储前端发送的地址数组
    private String[] address_arr;
    //临时存储前端传过来的开始时间和结束时间
    private String start;
    private String end;
    private int state_id;
    private String state_name;

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public int getState_id() {
        return state_id;
    }

    public void setState_id(int state_id) {
        this.state_id = state_id;
    }

    public int getActivity_team_num() {
        return activity_team_num;
    }

    public void setActivity_team_num(int activity_team_num) {
        this.activity_team_num = activity_team_num;
    }

    public int getActivity_update_by() {
        return activity_update_by;
    }

    public void setActivity_update_by(int activity_update_by) {
        this.activity_update_by = activity_update_by;
    }

    public Date getActivity_update_time() {
        return activity_update_time;
    }

    public void setActivity_update_time(Date activity_update_time) {
        this.activity_update_time = activity_update_time;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String[] getAddress_arr() {
        return address_arr;
    }

    public void setAddress_arr(String[] address_arr) {
        this.address_arr = address_arr;
    }

    public int getActivity_type() {
        return activity_type;
    }

    public void setActivity_type(int activity_type) {
        this.activity_type = activity_type;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getAddress_name() {
        return address_name;
    }

    public void setAddress_name(String address_name) {
        this.address_name = address_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
    }

    public int getActivity_publisher() {
        return activity_publisher;
    }

    public void setActivity_publisher(int activity_publisher) {
        this.activity_publisher = activity_publisher;
    }

    public Date getActivity_start_time() {
        return activity_start_time;
    }

    public void setActivity_start_time(Date activity_start_time) {
        this.activity_start_time = activity_start_time;
    }

    public Date getActivity_end_time() {
        return activity_end_time;
    }

    public void setActivity_end_time(Date activity_end_time) {
        this.activity_end_time = activity_end_time;
    }

    public Date getActivity_publish_time() {
        return activity_publish_time;
    }

    public void setActivity_publish_time(Date activity_publish_time) {
        this.activity_publish_time = activity_publish_time;
    }

    public String getActivity_title() {
        return activity_title;
    }

    public void setActivity_title(String activity_title) {
        this.activity_title = activity_title;
    }

    public int getActivity_number() {
        return activity_number;
    }

    public void setActivity_number(int activity_number) {
        this.activity_number = activity_number;
    }

    public int getActivity_real_number() {
        return activity_real_number;
    }

    public void setActivity_real_number(int activity_real_number) {
        this.activity_real_number = activity_real_number;
    }

    public int getActivity_area() {
        return activity_area;
    }

    public void setActivity_area(int activity_area) {
        this.activity_area = activity_area;
    }

    public String getActivity_content() {
        return activity_content;
    }

    public void setActivity_content(String activity_content) {
        this.activity_content = activity_content;
    }


}
