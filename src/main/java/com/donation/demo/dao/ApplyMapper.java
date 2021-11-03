package com.donation.demo.dao;

import com.donation.demo.model.Apply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApplyMapper {
    //申请时改变需求的状态
    boolean updateState(int demand_id);
    boolean toApply(Apply apply);
    List<Apply> getHelpList(int start, int pageSize, int id);
    int getMyTotal(int id);
    Apply get(int id);
    boolean updateApply(Apply apply);
    int getApplyTotalNum(int user_id);
    int getApplySuccessNum(int user_id);
    int getApplyingNum(int user_id);
    int getApplyFailNum(int user_id);
    List<Apply> checkIsFail(int demand_id,int apply_person);
    int getRealTotal(int demand_id);
    List<Apply> getDoingApply(Apply apply);
    List<Apply> getApplyByDAndU(Apply apply);
}
