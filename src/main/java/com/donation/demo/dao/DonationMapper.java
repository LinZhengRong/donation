package com.donation.demo.dao;

import com.donation.demo.model.Donation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DonationMapper {
    boolean insertOne(Donation donation);
    Donation get(int demand_id);
    boolean updateOne(Donation donation);
    //获取捐助信息
    List<Donation> getAllList(int start, int pageSize);
    int getTotal();
    //获取单个捐助信息
    Donation getAllInfo(int id);

    int getCheckId(int demand_id,int apply_id);
    String getReason(int demand_id);
}
