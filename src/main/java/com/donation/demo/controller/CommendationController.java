package com.donation.demo.controller;

import com.donation.demo.model.Apply;
import com.donation.demo.model.Donation;
import com.donation.demo.model.Result;
import com.donation.demo.model.commendation;
import com.donation.demo.service.ApplyService;
import com.donation.demo.service.CommendationService;
import com.donation.demo.service.DonationService;
import com.donation.demo.util.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@UserLoginToken
public class CommendationController {
    @Autowired
    private CommendationService commendationService;
    @Autowired
    private DonationService donationService;
    @Autowired
    private ApplyService applyService;

    @PostMapping("/api/commendation/getList/{pageNum}/{limit}")
    @ResponseBody
    public Result getList(@PathVariable("pageNum") int pageNum, @PathVariable("limit") int limit){
        List<commendation> list = commendationService.getList((pageNum-1)*limit,limit);
        int total = commendationService.getTotal();
        Map<String,Object> data=new HashMap<String,Object>();
        data.put("infoList",list);
        data.put("total",total);
        Result result = new Result(20000,"success",data);
        return result;
    }

    @PostMapping("/api/commendation/insertOne")
    @ResponseBody
    public Result insertOne(@RequestBody commendation commendation){
        Result result = new Result();
        commendation.setCommendation_time(new Date());
        Donation donation = donationService.getAllInfo(commendation.getCommendation_belond());
      
        int apply_id = donation.getApply().getApply_id();

        Apply apply = applyService.get(apply_id);
        int user_id = apply.getApply_person();
        commendation.setCommendation_person(user_id);
        if(commendationService.insertOne(commendation)){
            result = new Result(20000,"success",null);
        }else{
            result = new Result(20000,"error",null);
        }
        return result;
    }

    @PostMapping("/api/commendation/updateCon")
    @ResponseBody
    public Result updateCon(@RequestBody commendation commendation){
        Result result = new Result();
        if(commendationService.updateCon(commendation)){
            result = new Result(20000,"success",null);
        }else{
            result = new Result(20000,"fail",null);
        }
        return result;
    }

    @GetMapping("/api/commendation/delMany")
    @ResponseBody
    public Result delMany(@RequestParam("ids") String ids ){
        String[] idArray = ids.split(",");
        for (int i = 0; i < idArray.length; i++) {
            commendationService.delItem(Integer.parseInt(idArray[i]));
        }
        return new Result(20000,"success",null);
    }

    @GetMapping("/api/commendation/delItem")
    @ResponseBody
    public Result delItem(@RequestParam("commendation_id") int id ) {
        boolean isSuccess = commendationService.delItem(id);
        Result result = new Result(20000,"success",isSuccess);
        return result;
    }
}
