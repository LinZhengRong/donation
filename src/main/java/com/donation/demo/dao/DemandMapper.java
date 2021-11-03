package com.donation.demo.dao;

import com.donation.demo.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DemandMapper {
    List<Demand> getList(int start,int pageSize,int id);
    List<Demand> getListByNick(String user_nick,int start,int pageSize);
    List<Demand> getListHasPass(int start,int pageSize);
    int getTotal(int id);
    int getTotalHasPass();
    int getListTotal(Demand demand);
    Demand get(int demand_id);
    List<Type> getType();
    boolean insertAddress(Address addr);
    boolean updateAddress(Address addr);
    boolean addDemand(Demand demand);
    boolean insertPic(Picture picture);
    boolean checkDemand(Demand demand);
    Demand getApplyPeople(int demand_id,int start,int pageSize);
    boolean updateDemand(Demand demand);
    boolean updateTime(Demand demand);
    List<Demand> getPics(Demand demand,int start,int pageSize);
    Demand getPic(int id);
    int getPersonNum(int id);
    List<Demand> findDemandHasChange();
    boolean updateNum(Demand demand);
}
