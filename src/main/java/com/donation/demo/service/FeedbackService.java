package com.donation.demo.service;

import com.donation.demo.model.Feedback;

import java.util.List;

public interface FeedbackService {
    List<Feedback> getFeedbackList(int start,int pageSize);
    boolean add(Feedback feedback);
    int getTotal();
    boolean delItem(int id);
}
