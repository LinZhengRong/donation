package com.donation.demo.controller;

import com.auth0.jwt.JWT;
import com.donation.demo.model.*;
import com.donation.demo.service.ApplyService;
import com.donation.demo.service.DemandService;
import com.donation.demo.service.DonationService;
import com.donation.demo.util.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@UserLoginToken
public class DonationController {
    @Autowired
    private DonationService donationService;
    @Autowired
    private DemandService demandService;
    @Autowired
    private ApplyService applyService;


    //需求方确认物资送达
    @PostMapping("/api/donation/uploadInfo")
    @ResponseBody
    private Result uploadInfo(@RequestBody Donation donation){
        Result result = new Result();
            donation.setCheck_time(new Date());
            donation.setState_id(7);
            //修改对应demand的状态
            Demand demand = new Demand();
            demand.setDemand_id(donation.getDemand_id());
            demand.setDemand_status(7);
            demandService.updateDemand(demand);
            //修改对应apply的状态
            Apply apply = new Apply();
            apply.setApply_id(donation.getApply_id());
            apply.setState_id(7);
            applyService.updateApply(apply);
            if(donationService.updateOne(donation)){
                result = new Result(20000,"success",null);
            }else{
                result = new Result(20000,"error",null);
            }

        return result;
    }

    //
    @GetMapping("/api/donation/get")
    @ResponseBody
    private Result get(@RequestParam("id") int demand_id){
        Donation donation = donationService.get(demand_id);
        Map<String,Object> data=new HashMap<String,Object>();
        data.put("demandCheck", donation);
        Result result = new Result(20000,"success",data);
        return result;
    }


    //需求方确认物资
    @PostMapping("/api/donation/update/{num}")
    @ResponseBody
    private Result update(@RequestBody Donation donation, @PathVariable("num") int num){
        Result result = new Result();
        //更新apply
        Apply apply = applyService.get(donation.getApply_id());
        apply.setState_id(donation.getState_id());
        apply.setReal_num(num);
        apply.setCheck_time(new Date());
        applyService.updateApply(apply);
        //更新donation
        int check_id = donationService.getCheckId(donation.getDemand_id(),donation.getApply_id());
        Donation donation1 = donationService.getAllInfo(check_id);
        donation1.setCheck_time(new Date());
        donation1.setState_id(donation.getState_id());
        if(donationService.updateOne(donation1)){
            result = new Result(20000,"success",null);
        }else{
            result = new Result(20000,"error",null);
        }
        return result;
    }


    @PostMapping("/api/donation/list/{pageNum}/{limit}")
    @ResponseBody
    public Result list(Donation donation, @PathVariable("pageNum") int pageNum, @PathVariable("limit") int pageSize){
        List<Donation> list = donationService.getAllList((pageNum-1)*pageSize,pageSize);
        int total = donationService.getTotal();
        Map<String,Object> data=new HashMap<String,Object>();
        data.put("infoList",list);
        data.put("total",total);
        Result result = new Result(20000,"success",data);
        return result;
    }

    @GetMapping("/api/donation/getAllInfo")
    @ResponseBody
    public Result getAllInfo(@RequestParam("id") int donation_id){
        Donation info  = donationService.getAllInfo(donation_id);
        Map<String,Object> data=new HashMap<String,Object>();
        data.put("info",info);
        Result result = new Result(20000,"success",data);
        return result;
    }

    //根据demand_id和apply_id获取donation_id
    @PostMapping("/api/donation/getAllInfoByDidAndAid")
    @ResponseBody
    public Result getAllInfoByDidAndAid(@RequestBody Donation donation){
        int donation_id = donationService.getCheckId(donation.getDemand_id(), donation.getApply_id());
        Donation info  = donationService.getAllInfo(donation_id);
        Map<String,Object> data=new HashMap<String,Object>();
        data.put("info",info);
        Result result = new Result(20000,"success",data);
        return result;
    }

    /*获取捐助的原因*/
    @GetMapping("/api/donation/getFailReason")
    @ResponseBody
    public Result getFailReason(@RequestParam ("id") int demand_id){
        String reason = donationService.getReason(demand_id);
        Map<String,Object> data=new HashMap<String,Object>();
        data.put("reason",reason);
        Result result = new Result(20000,"success",data);
        return result;
    }



    @PostMapping("/api/donation/updateInfo")
    @ResponseBody
    public Result donationUpdate(@RequestBody Donation donation, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        String user_id = JWT.decode(token).getAudience().get(0);
        Result result = new Result();
        String[] address = donation.getAddress_arr();
        Address addr = new Address();
        addr.setAddress_id(donation.getAddress().getAddress_id());
        addr.setAddress_province(address[0]);
        addr.setAddress_city(address[1]);
        addr.setAddress_district(address[2]);
        addr.setAddress_detail(address[3]);
        //修改地址
        if(demandService.updateAddress(addr)){
            if(demandService.updateNum(donation.getDemand())){
                result = new Result(20000,"success",null);
            }else{
                result = new Result(20000,"success",null);
            }
        }
        return result;
    }
}
