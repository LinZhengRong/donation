package com.donation.demo.dao;

import com.donation.demo.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    //判断用户是否存在
    User hasUserByName(String user_name);
    //判断用户是否存在
    User hasUser(int user_id);
    //查询用户列表
    List<User> getUserlist(User user,int start,int pageSize);
    //返回用户总数
    int getUserListTotal(User user);
    //修改用户信息
    boolean updateUser(User user);
    //禁用用户
    boolean updateState(User user);

    boolean reg(User user);
    User getByCondition(User user);
}


