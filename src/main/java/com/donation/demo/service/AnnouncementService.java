package com.donation.demo.service;

import com.donation.demo.model.Image;
import com.donation.demo.model.announcement;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface AnnouncementService {
    List<announcement> getInfo(announcement announcement,int start,int pageSize);
    int getCount(announcement announcement);
    boolean addAnnouncement(announcement announcement);
    Image uploadImage(MultipartFile multipartFile);
    boolean delItem(int id);
    announcement get(int id);
    boolean updateOne(announcement announcement);
}
