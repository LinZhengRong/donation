package com.donation.demo.service.impl;

import com.donation.demo.dao.ThankMapper;
import com.donation.demo.model.Thank;
import com.donation.demo.service.ThankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThankServiceImpl implements ThankService {
    @Autowired
    private ThankMapper thankMapper;
    @Override
    public boolean add(Thank thank) {
        return thankMapper.add(thank);
    }
}
