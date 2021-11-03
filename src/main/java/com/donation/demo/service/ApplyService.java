package com.donation.demo.service;

import com.donation.demo.model.Apply;

import java.util.List;

public interface ApplyService {
    boolean updateState(int demand_id);
    boolean toApply(Apply apply);

    List<Apply> getHelpList(int start, int pageSize, int id);
    int getMyTotal(int id);
    Apply get(int id);
    Boolean updateApply(Apply apply);
    int[] getApplyNum(int user_id);
    List<Apply> checkIsFail(int demand_id,int apply_id);
    int getRealTotal(int demand_id);
    List<Apply> getDoingApply(Apply apply);
    List<Apply> getApplyByDAndU(Apply apply);
}
