package com.donation.demo.service;

import com.donation.demo.model.Donation;

import java.util.List;

public interface DonationService {
    boolean insertOne(Donation donation);
    Donation get(int demand_id);
    boolean updateOne(Donation donation);
    List<Donation> getAllList(int start, int pageSize);
    int getTotal();
    Donation getAllInfo(int id);
    int getCheckId(int demand_id,int apply_id);
    String getReason(int demand_id);
}
