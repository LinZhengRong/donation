package com.donation.demo.service;

import com.donation.demo.model.User;

import java.util.List;

public interface UserService {
    User hasUserByName(String user_name);
    User hasUser(int user_id);
    List<User> getUserList(User user,int start,int pageSize);
    int getUserListTotal(User user);
    boolean updateUser(User user);
    boolean updateState(User user);
    boolean reg(User user);
    User getByCondition(User user);

}
