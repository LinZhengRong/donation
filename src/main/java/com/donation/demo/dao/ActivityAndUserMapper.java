package com.donation.demo.dao;

import com.donation.demo.model.ActivityAndUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActivityAndUserMapper {
    boolean insertOne(ActivityAndUser activityAndUser);
    ActivityAndUser checkHasJoin(ActivityAndUser activityAndUser);
    List<ActivityAndUser> getPeople(int id);
}
