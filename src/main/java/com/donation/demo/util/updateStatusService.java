package com.donation.demo.util;

import com.donation.demo.model.Activity;
import com.donation.demo.model.Apply;
import com.donation.demo.model.Demand;
import com.donation.demo.service.ActivityService;
import com.donation.demo.service.ApplyService;
import com.donation.demo.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class updateStatusService {
    //每一个小时执行一次
    //@Scheduled(cron = "0 0 * * * ?")

    @Autowired
    private ActivityService activityService;
    @Autowired
    private DemandService demandService;
    @Autowired
    private ApplyService applyService;

   @Scheduled(cron = "*/5 * * * * ?")
    public void dump()throws Exception{
        //查找已开始的活动
        List<Activity> activityStartList = activityService.findActHasChange(0);
        if(activityStartList.size()!=0){
            for(int i =0;i<activityStartList.size();i++){
                activityStartList.get(i).setState_id(11);
                activityService.update(activityStartList.get(i));
            }
        }
        //查找已结束的活动
        List<Activity> activityEndList = activityService.findActHasChange(1);
        if(activityEndList.size()!=0){
            for(int i =0;i<activityEndList.size();i++){
                activityEndList.get(i).setState_id(12);
                activityService.update(activityEndList.get(i));
            }
        }
        //查找已结束的需求
        List<Demand> demandList = demandService.findDemandHasChange();
        if(demandList.size()!=0){
            for(int i =0;i<demandList.size();i++){
                //获取该需求对应的未完成的捐助
                Apply apply = new Apply();
                apply.setState_id(6);
                apply.setDemand_id(demandList.get(i).getDemand_id());
                List<Apply> applyList = applyService.getDoingApply(apply);
                //更新状态
                if(applyList.size()!=0){
                    for(int j=0;j<applyList.size();j++){
                        applyList.get(j).setState_id(13);
                        applyService.updateApply(applyList.get(j));
                    }
                }
                demandList.get(i).setDemand_status(9);
                demandService.updateDemand(demandList.get(i));
            }
        }
    }
}
