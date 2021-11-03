package com.donation.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class Demand {
    private int demand_id;
    private int demand_number;
    private int demand_type_id;
    private int demand_destination;
    private int demand_publisher;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date demand_check_time;
    private String demand_check_reason;
    private int demand_status;
    private String demand_level;
    private String demand_desc;


    //关联状态表
    private String state_desc;
    private int state_id;
    //关联用户表
    private String user_name;
    private String user_demand_nick; //需求人昵称
    private int user_type;
    //关联tb_apply
    private int apply_person;
    private String apply_person_name;
    private int apply_id;
    private String apply_pic_path;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date demand_time;
    private String address_name;
    private String[] address_arr;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date demand_end_time;
    private String demand_name;
    //关联物资类型表e
    private String type_name;
    private int demand_pic;
    private String demand_pic_name;
    //关联图片表
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date pic_upload_time;
    private String pic_path;
    private String demand_addr;
    //临时存储前端传输的时间
    private String end_time;


    private User user;
    private List<Apply> applyList;

    public List<Apply> getApplyList() {
        return applyList;
    }

    public void setApplyList(List<Apply> applyList) {
        this.applyList = applyList;
    }

    public String getApply_pic_path() {
        return apply_pic_path;
    }

    public void setApply_pic_path(String apply_pic_path) {
        this.apply_pic_path = apply_pic_path;
    }

    public int getState_id() {
        return state_id;
    }

    public void setState_id(int state_id) {
        this.state_id = state_id;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDemand_level() {
        return demand_level;
    }

    public void setDemand_level(String demand_level) {
        this.demand_level = demand_level;
    }

    public String getDemand_desc() {
        return demand_desc;
    }

    public void setDemand_desc(String demand_desc) {
        this.demand_desc = demand_desc;
    }

    public String getUser_demand_nick() {
        return user_demand_nick;
    }

    public void setUser_demand_nick(String user_demand_nick) {
        this.user_demand_nick = user_demand_nick;
    }

    public int getDemand_status() {
        return demand_status;
    }

    public void setDemand_status(int demand_status) {
        this.demand_status = demand_status;
    }

    public int getApply_id() {
        return apply_id;
    }

    public void setApply_id(int apply_id) {
        this.apply_id = apply_id;
    }

    public String getApply_person_name() {
        return apply_person_name;
    }

    public void setApply_person_name(String apply_person_name) {
        this.apply_person_name = apply_person_name;
    }

    public int getApply_person() {
        return apply_person;
    }

    public void setApply_person(int apply_person) {
        this.apply_person = apply_person;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public Date getDemand_check_time() {
        return demand_check_time;
    }

    public void setDemand_check_time(Date demand_check_time) {
        this.demand_check_time = demand_check_time;
    }

    public String getDemand_check_reason() {
        return demand_check_reason;
    }

    public void setDemand_check_reason(String demand_check_reason) {
        this.demand_check_reason = demand_check_reason;
    }



    public String getPic_path() {
        return pic_path;
    }

    public void setPic_path(String pic_path) {
        this.pic_path = pic_path;
    }

    public String getState_desc() {
        return state_desc;
    }

    public void setState_desc(String state_desc) {
        this.state_desc = state_desc;
    }


    public int getDemand_publisher() {
        return demand_publisher;
    }

    public void setDemand_publisher(int demand_publisher) {
        this.demand_publisher = demand_publisher;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }


    public Date getDemand_time() {
        return demand_time;
    }

    public void setDemand_time(Date demand_time) {
        this.demand_time = demand_time;
    }




    public Date getDemand_end_time() {
        return demand_end_time;
    }

    public void setDemand_end_time(Date demand_end_time) {
        this.demand_end_time = demand_end_time;
    }

    public String getDemand_name() {
        return demand_name;
    }

    public void setDemand_name(String demand_name) {
        this.demand_name = demand_name;
    }



    public int getDemand_pic() {
        return demand_pic;
    }

    public void setDemand_pic(int demand_pic) {
        this.demand_pic = demand_pic;
    }



    public String getDemand_pic_name() {
        return demand_pic_name;
    }

    public void setDemand_pic_name(String demand_pic_name) {
        this.demand_pic_name = demand_pic_name;
    }



    public String[] getAddress_arr() {
        return address_arr;
    }

    public void setAddress_arr(String[] address_arr) {
        this.address_arr = address_arr;
    }



    public Date getPic_upload_time() {
        return pic_upload_time;
    }

    public void setPic_upload_time(Date pic_upload_time) {
        this.pic_upload_time = pic_upload_time;
    }




    public String getDemand_addr() {
        return demand_addr;
    }

    public void setDemand_addr(String demand_addr) {
        this.demand_addr = demand_addr;
    }



    public String getAddress_name() {
        return address_name;
    }

    public void setAddress_name(String address_name) {
        this.address_name = address_name;
    }





    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }






    public int getDemand_id() {
        return demand_id;
    }

    public void setDemand_id(int demand_id) {
        this.demand_id = demand_id;
    }

    public int getDemand_number() {
        return demand_number;
    }

    public void setDemand_number(int demand_number) {
        this.demand_number = demand_number;
    }

    public int getDemand_type_id() {
        return demand_type_id;
    }

    public void setDemand_type_id(int demand_type_id) {
        this.demand_type_id = demand_type_id;
    }

    public int getDemand_destination() {
        return demand_destination;
    }

    public void setDemand_destination(int demand_destination) {
        this.demand_destination = demand_destination;
    }


}
