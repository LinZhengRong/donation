package com.donation.demo.model;

public class User {
    private int user_id;
    private String user_password;
    private String user_name;
    private int user_type;
    private String user_ID_number;
    private String user_QQ;
    private String user_telephone;
    private String user_nick;
    private int user_credential;
    private String user_email;
    //新密码
    private String user_new_password;
    //证件资质存储的地址
    private String img;


    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getUser_credential() {
        return user_credential;
    }

    public void setUser_credential(int user_credential) {
        this.user_credential = user_credential;
    }

    public String getUser_new_password() {
        return user_new_password;
    }

    public void setUser_new_password(String user_new_password) {
        this.user_new_password = user_new_password;
    }

    public String getUser_nick() {
        return user_nick;
    }

    public void setUser_nick(String user_nick) {
        this.user_nick = user_nick;
    }

    //关联tb_authority
    private String authority_name;


    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_password='" + user_password + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_type=" + user_type +
                ", user_ID_number='" + user_ID_number + '\'' +
                ", user_QQ=" + user_QQ +
                ", user_telephone='" + user_telephone + '\'' +
                ", user_state=" + user_state +
                ", user_authority=" + user_authority +
                '}';
    }

    private int user_state;
    private int user_authority;
    public int getUser_id() {
        return user_id;
    }
    public String getAuthority_name() {
        return authority_name;
    }

    public void setAuthority_name(String authority_name) {
        this.authority_name = authority_name;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public String getUser_ID_number() {
        return user_ID_number;
    }

    public void setUser_ID_number(String user_ID_number) {
        this.user_ID_number = user_ID_number;
    }

    public String getUser_QQ() {
        return user_QQ;
    }

    public void setUser_QQ(String user_QQ) {
        this.user_QQ = user_QQ;
    }

    public String getUser_telephone() {
        return user_telephone;
    }

    public void setUser_telephone(String user_telephone) {
        this.user_telephone = user_telephone;
    }

    public int getUser_state() {
        return user_state;
    }

    public void setUser_state(int user_state) {
        this.user_state = user_state;
    }

    public int getUser_authority() {
        return user_authority;
    }

    public void setUser_authority(int user_authority) {
        this.user_authority = user_authority;
    }


    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
}
