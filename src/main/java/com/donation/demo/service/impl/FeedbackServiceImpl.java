package com.donation.demo.service.impl;

import com.donation.demo.dao.FeedbackMapper;
import com.donation.demo.model.Feedback;
import com.donation.demo.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackMapper feedbackMapper;
    @Override
    public List<Feedback> getFeedbackList(int start,int pageSize) {
        return feedbackMapper.getFeedbackList(start, pageSize);
    }

    @Override
    public boolean add(Feedback feedback) {
        return feedbackMapper.add(feedback);
    }

    @Override
    public int getTotal() {
        return feedbackMapper.getTotal();
    }

    @Override
    public boolean delItem(int id) {
        return feedbackMapper.del(id);
    }
}
