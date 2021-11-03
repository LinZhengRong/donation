package com.donation.demo.service;

import com.donation.demo.model.Activity;
import com.donation.demo.model.ActivityAndUser;
import com.donation.demo.model.ActivityType;

import java.util.List;

public interface ActivityService {
    List<Activity> getList(Activity activity,int start,int pageSize);
    int getCount();
    Activity get(int id);
    boolean updateOne(int id,int num);
    boolean joinActivity(ActivityAndUser activityAndUser);
    List<ActivityType> getType();
    boolean add(Activity activity);
    boolean update(Activity activity);
    ActivityAndUser checkHasJoin (ActivityAndUser activityAndUser);
    List<ActivityAndUser> getPeople(int id);
    List<Activity> findActHasChange(int type);
    List<Activity> getMyActivity(int id,int start,int pageSize);
    boolean delItem(int id);
}
