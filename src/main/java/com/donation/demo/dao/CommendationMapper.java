package com.donation.demo.dao;

import com.donation.demo.model.commendation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommendationMapper {
    List<commendation> getList(int start,int pageSize);
    int getTotal();
    boolean insertOne(commendation commendation);
    boolean updateCon(commendation commendation);
    boolean delOne(int id);

}
