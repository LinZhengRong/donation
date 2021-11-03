package com.donation.demo.service.impl;

import com.donation.demo.dao.UserMapper;
import com.donation.demo.model.User;
import com.donation.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User hasUserByName(String user_name) {
        return userMapper.hasUserByName(user_name);
    }

    @Override
    public User hasUser(int user_id) {
        return userMapper.hasUser(user_id);
    }

    @Override
    public List<User> getUserList(User user,int start,int pageSize) {
        return userMapper.getUserlist(user,start,pageSize);
    }

    @Override
    public int getUserListTotal(User user) {
        return userMapper.getUserListTotal(user);
    }

    @Override
    public boolean updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public boolean updateState(User user) {
        return userMapper.updateState(user);
    }

    @Override
    public boolean reg(User user) {
        return userMapper.reg(user);
    }

    @Override
    public User getByCondition(User user) {
        return userMapper.getByCondition(user);
    }


}
