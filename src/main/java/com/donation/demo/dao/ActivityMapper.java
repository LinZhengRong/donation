package com.donation.demo.dao;

import com.donation.demo.model.Activity;
import com.donation.demo.model.ActivityType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActivityMapper {
    List<Activity> getList(Activity activity,int start,int pageSize);
    int getCount();
    Activity get(int id);
    boolean updateOne(int id,int num);
    boolean delItem(int id);
    List<ActivityType> getType();
    boolean add(Activity activity);
    boolean update(Activity activity);
    List<Activity> findActHasChange(int type);
    List<Activity> getMyActivity(int id,int start,int pageSize);
}
