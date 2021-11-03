package com.donation.demo.service.impl;

import com.donation.demo.dao.ApplyMapper;
import com.donation.demo.model.Apply;
import com.donation.demo.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyServiceImpl implements ApplyService {
    @Autowired
    private ApplyMapper applyMapper;
    @Override
    public boolean updateState(int demand_id) {
        return applyMapper.updateState(demand_id);
    }

    @Override
    public boolean toApply(Apply apply) {
        return applyMapper.toApply(apply);
    }

    @Override
    public List<Apply> getHelpList(int start, int pageSize, int id) {
        return applyMapper.getHelpList(start,pageSize,id);
    }

    @Override
    public int getMyTotal(int id) {
        return applyMapper.getMyTotal(id);
    }

    @Override
    public Apply get(int id) {
        return applyMapper.get(id);
    }

    @Override
    public Boolean updateApply(Apply apply) {
        return applyMapper.updateApply(apply);
    }

    @Override
    public int[] getApplyNum(int user_id) {
        int[] nums = new int[4];
        nums[0]=applyMapper.getApplyTotalNum(user_id);
        nums[1]=applyMapper.getApplySuccessNum(user_id);
        nums[2]=applyMapper.getApplyingNum(user_id);
        nums[3]=applyMapper.getApplyFailNum(user_id);
        return nums;
    }

    @Override
    public List<Apply> checkIsFail(int demand_id, int apply_id) {
        return applyMapper.checkIsFail(demand_id,apply_id);
    }

    @Override
    public int getRealTotal(int demand_id) {
        return applyMapper.getRealTotal(demand_id);
    }

    @Override
    public List<Apply> getDoingApply(Apply apply) {
        return applyMapper.getDoingApply(apply);
    }

    @Override
    public List<Apply> getApplyByDAndU(Apply apply) {
        return applyMapper.getApplyByDAndU(apply);
    }
}
