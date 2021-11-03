package com.donation.demo.dao;

import com.donation.demo.model.announcement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnnouncementMapper {
    List<announcement> getInfo(announcement announcement,int start,int pageSize);
    int getCount(announcement announcement);
    boolean addAnnouncement(announcement announcement);
    boolean delItem(int id);
    announcement get(int id);
    boolean updateOne(announcement announcement);

}
