package com.donation.demo.service;

import com.donation.demo.model.commendation;

import java.util.List;

public interface CommendationService {
    List<commendation> getList(int start,int pageSize);
    int getTotal();
    boolean insertOne(commendation commendation);
    boolean updateCon(commendation commendation);
    boolean delItem(int id);
}
