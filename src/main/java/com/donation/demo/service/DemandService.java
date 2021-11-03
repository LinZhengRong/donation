package com.donation.demo.service;

import com.donation.demo.model.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DemandService {
    List<Demand> getList(String user_nick,int start, int pageSize);
    List<Demand> getMyDemand(int start, int pageSize,int id);
    List<Demand> getListHasPass(int start, int pageSize);
    int getTotal();
    int getListTotal(Demand demand);
    int getMyTotal(int id);
    int getTotalHasPass();
    Demand get(int demand_id);
    List<Type> getType();
    Image uploadImage(MultipartFile multipartFile);
    boolean insertAddress(Address addr);
    boolean updateAddress(Address addr);
    boolean addDemand(Demand demand);
    boolean insertPic(Picture picture);
    boolean checkDemand(Demand demand);
    Demand getApplyPeople(int demand_id,int start, int pageSize);
    Boolean updateDemand(Demand demand);
    boolean updateTime(Demand demand);
    List<Demand> getPics(Demand demand,int start,int pageSize);
    Demand getPic(int id);
    int getPersonNum(int id);
    boolean finish(Demand demand);
    List<Demand> findDemandHasChange();
    boolean updateNum(Demand demand);
}
