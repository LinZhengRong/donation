package com.donation.demo.controller;

import com.auth0.jwt.JWT;
import com.donation.demo.model.*;
import com.donation.demo.service.ApplyService;
import com.donation.demo.service.DemandService;
import com.donation.demo.service.DonationService;
import com.donation.demo.util.SendService;
import com.donation.demo.util.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@UserLoginToken
public class ApplyController {
    @Autowired
    private ApplyService applyService;
    @Autowired
    private DemandService demandService;
    @Autowired
    private DonationService donationService;
    @Autowired
    private SendService sendService;
    @Autowired
    JavaMailSenderImpl mailSender;
    @PostMapping("/api/apply/toApply")
    @ResponseBody
    public Result toApply(@RequestBody Apply apply, HttpServletRequest httpServletRequest) {
        Result result = new Result();
        String token = httpServletRequest.getHeader("token");
        String apply_person = JWT.decode(token).getAudience().get(0);
        //核对数量
        Demand demand = demandService.get(apply.getDemand_id());
        int num = demand.getDemand_number();
        int realNum = applyService.getRealTotal(apply.getDemand_id());
        if(apply.getPlan_num() > num-realNum){
            result =  new Result(20000, "numError", null);
            return result;
        }
        apply.setApply_time(new Date());
        apply.setState_id(4);
        apply.setPlan_num(apply.getPlan_num());
        apply.setApply_person(Integer.parseInt(apply_person));
        //插入图片
        Picture picture = new Picture();
        picture.setPic_path(apply.getPic_path());
        picture.setPic_upload_time(new Date());
        demandService.insertPic(picture);
        //设置图片的id
        apply.setPic_id(picture.getPic_id());
        //新增apply数据
        if (applyService.toApply(apply)) {
            result = new Result(20000, "success", null);
        } else {
            result = new Result(20000, "error", null);
        }
        return result;
    }


    @PostMapping("/api/apply/getDoingApply")
    @ResponseBody
    public Result getDoingApply(@RequestBody Apply apply, HttpServletRequest httpServletRequest) {
        Result result = new Result();
        List<Apply> applyList = applyService.getDoingApply(apply);
        if(applyList.size() == 0){
            result = new Result(20000, "success", null);
        }else{
            result = new Result(20000, "error", null);
        }
        return result;
    }


    //获取捐助方是否有同个需求的申请
    @PostMapping("/api/apply/checkSameApply")
    @ResponseBody
    public Result checkSameApply(@RequestBody Apply apply, HttpServletRequest httpServletRequest) {
        Result result = new Result();
        String token = httpServletRequest.getHeader("token");
        String apply_person = JWT.decode(token).getAudience().get(0);
        apply.setApply_person(Integer.parseInt(apply_person));
        List<Apply> applyList = applyService.getApplyByDAndU(apply);
        if(applyList.size() == 0){
            result = new Result(20000, "success", null);
        }else{
            result = new Result(20000, "error", null);
        }
        return result;
    }



    @PostMapping("/api/apply/getMyHelp/{pageNum}/{limit}")
    @ResponseBody
    public Result getMyHelp(@PathVariable("pageNum") int pageNum, @PathVariable("limit") int pageSize, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        String user_id = JWT.decode(token).getAudience().get(0);
        List<Apply> list = applyService.getHelpList((pageNum - 1) * pageSize, pageSize, Integer.parseInt(user_id));
        int total = applyService.getMyTotal(Integer.parseInt(user_id));
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("infoList", list);
        data.put("total", total);
        Result result = new Result(20000, "success", data);
        return result;
    }


    @GetMapping("/api/apply/checkApply")
    @ResponseBody
    public Result checkApply(@RequestParam("id") int id, @RequestParam("isSuccess") int isSuccess) {
        Apply apply = applyService.get(id);
        Result result = new Result();
        String html ="";
        Demand demand = demandService.get(apply.getDemand_id());
        String name = demand.getUser().getUser_nick();
        String email = apply.getUser_email();
        String helpName = apply.getUser_apply_nick();
        if (isSuccess == 0) {
            //捐助资格审核未通过
            apply.setState_id(5);
            applyService.updateApply(apply);
            html=    " <div style='font-weight:bold;'>亲爱的"+helpName+"，您好！"+"</div>"
                    +"<div style='text-indent:2em'>很遗憾地通知您，您在"+new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss").format(apply.getApply_time())+"向需求方"
                    +name+"发出的捐助申请因为其他个别因素未通过，</div>"
                    +"<div>再次感谢您对本平台的支持。</div>";
            result = new Result(20000, "success", null);
        } else {
            //捐助资格审核通过
            apply.setState_id(6);
            //新增donation
            Donation donation = new Donation();
            donation.setDemand_id(apply.getDemand_id());
            donation.setApply_id(id);
            donation.setState_id(6);
            donation.setSubmit_time(new Date());
            if(donationService.insertOne(donation)){
                result = new Result(20000, "success", null);
            }else{
                result = new Result(20000, "error", null);
            }
            apply.setCheck_time(new Date());
            applyService.updateApply(apply);
            html=   " <div style='font-weight:bold;'>亲爱的"+helpName+"，您好！"+"</div>"
                    +"<div style='text-indent:2em'>您在"+new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss").format(apply.getApply_time())+"向需求方"
                    +name+"发出的捐助申请已通过，</div>"
                    +"<div>现在可以开始进行物资的捐助了。</div>";
        }
        //1、创建消息邮件
        MimeMessage mineMessage=mailSender.createMimeMessage();
        //2、创建helper
        MimeMessageHelper messageHelper= null;
        try {
            messageHelper = new MimeMessageHelper(mineMessage,true);
            //添加复杂的邮件信息
            messageHelper.setText(html,true);
            messageHelper.setSubject("通知");
            messageHelper.setTo(email);  //收件人
            messageHelper.setFrom("1973195349@qq.com");//发送人
            mailSender.send(mineMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/api/apply/getNum")
    @ResponseBody
    public Result getNum(@RequestParam("user_id") int user_id){
        int[] nums = applyService.getApplyNum(user_id);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("nums", nums);
        Result result = new Result(20000, "success", data);
        return result;
    }



    @GetMapping("/api/apply/checkIsFail")
    @ResponseBody
    public Result checkIsFail(@RequestParam("id") int demand_id,HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        String user_id = JWT.decode(token).getAudience().get(0);
        List<Apply> list = applyService.checkIsFail(demand_id,Integer.parseInt(user_id));
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("list", list);
        Result result = new Result(20000, "success", data);
        return result;
    }

}
