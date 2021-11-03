package com.donation.demo.service.impl;

import com.donation.demo.dao.DonationMapper;
import com.donation.demo.model.Donation;
import com.donation.demo.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationServiceImpl implements DonationService {
    @Autowired
    private DonationMapper donationMapper;

    @Override
    public boolean insertOne(Donation donation) {
        return donationMapper.insertOne(donation);
    }

    @Override
    public Donation get(int demand_id) {
        return donationMapper.get(demand_id);
    }

    @Override
    public boolean updateOne(Donation donation) {
        return donationMapper.updateOne(donation);
    }

    @Override
    public List<Donation> getAllList(int start, int pageSize) {
        return donationMapper.getAllList(start,pageSize);
    }

    @Override
    public int getTotal() {
        return donationMapper.getTotal();
    }

    @Override
    public Donation getAllInfo(int id) {
        return donationMapper.getAllInfo(id);
    }

    @Override
    public int getCheckId(int demand_id, int apply_id) {
        return donationMapper.getCheckId(demand_id,apply_id);
    }

    @Override
    public String getReason(int demand_id) {
        return donationMapper.getReason(demand_id);
    }


}
