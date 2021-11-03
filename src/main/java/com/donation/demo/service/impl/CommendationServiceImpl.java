package com.donation.demo.service.impl;

import com.donation.demo.dao.CommendationMapper;
import com.donation.demo.model.commendation;
import com.donation.demo.service.CommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommendationServiceImpl implements CommendationService {
    @Autowired
    private CommendationMapper commendationMapper;
    @Override
    public List<commendation> getList(int start,int pageSize) {
        return commendationMapper.getList(start,pageSize);
    }

    @Override
    public int getTotal() {
        return commendationMapper.getTotal();
    }

    @Override
    public boolean insertOne(commendation commendation) {
        return commendationMapper.insertOne(commendation);
    }

    @Override
    public boolean updateCon(commendation commendation) {
        return commendationMapper.updateCon(commendation);
    }

    @Override
    public boolean delItem(int id) {
        return commendationMapper.delOne(id);
    }
}
