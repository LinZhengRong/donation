package com.donation.demo.service.impl;

import com.donation.demo.dao.ActivityAndUserMapper;
import com.donation.demo.dao.ActivityMapper;
import com.donation.demo.model.Activity;
import com.donation.demo.model.ActivityAndUser;
import com.donation.demo.model.ActivityType;
import com.donation.demo.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ActivityServiceImpl  implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper ;
    @Autowired
    private ActivityAndUserMapper activityAndUserMapper ;
    @Override
    public List<Activity> getList(Activity activity,int start,int pageSize) {
        return activityMapper.getList(activity,start,pageSize);
    }

    @Override
    public int getCount() {
        return activityMapper.getCount();
    }

    @Override
    public Activity get(int id) {
        return activityMapper.get(id);
    }

    @Override
    public boolean updateOne(int id,int num) {
        return activityMapper.updateOne(id,num);
    }

    @Override
    public boolean joinActivity(ActivityAndUser activityAndUser) {
        return activityAndUserMapper.insertOne(activityAndUser);
    }

    @Override
    public List<ActivityType> getType() {
        return activityMapper.getType();
    }

    @Override
    public boolean add(Activity activity) {
        return activityMapper.add(activity);
    }

    @Override
    public boolean update(Activity activity) {
        return activityMapper.update(activity);
    }

    @Override
    public ActivityAndUser checkHasJoin(ActivityAndUser activityAndUser) {
        return activityAndUserMapper.checkHasJoin(activityAndUser);
    }

    @Override
    public List<ActivityAndUser> getPeople(int id) {
        return activityAndUserMapper.getPeople(id);
    }

    @Override
    public List<Activity> findActHasChange(int type) {
        return activityMapper.findActHasChange(type);
    }

    @Override
    public List<Activity> getMyActivity(int id, int start, int pageSize) {
        return activityMapper.getMyActivity(id,start,pageSize);
    }

    @Override
    public boolean delItem(int id) {
        return activityMapper.delItem(id);
    }


}
