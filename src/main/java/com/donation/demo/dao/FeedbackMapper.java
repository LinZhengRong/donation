package com.donation.demo.dao;

import com.donation.demo.model.Feedback;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface FeedbackMapper {
    List<Feedback> getFeedbackList(int start,int pageSize);
    boolean add(Feedback feedback);
    int getTotal();
    boolean del(int id);
}
