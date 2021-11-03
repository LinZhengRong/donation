package com.donation.demo.controller;

import com.donation.demo.model.Apply;
import com.donation.demo.model.Result;
import com.donation.demo.model.Thank;
import com.donation.demo.service.ApplyService;
import com.donation.demo.service.DonationService;
import com.donation.demo.service.ThankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Controller
public class ThankController {
    @Autowired
    private ThankService thankService;
    @Autowired
    private DonationService donationService;
    @Autowired
    private ApplyService applyService;
    @Autowired
    JavaMailSenderImpl mailSender;

    @PostMapping("/api/thank/add")
    @ResponseBody
    public Result getList(@RequestBody Thank thank){
        Result result = new Result();
        thank.setThank_time(new Date());
        if(thankService.add(thank)){
            result = new Result(20000,"success",null);
        }else{
            result = new Result(20000,"error",null);
        }
        int apply_id =  donationService.getAllInfo(thank.getDonation_id()).getApply().getApply_id();
        System.out.println(apply_id);
        Apply apply = applyService.get(apply_id);
        //发送邮件
        String html= thank.getThank_content();

        //1、创建一个复杂的消息邮件
        MimeMessage mineMessage=mailSender.createMimeMessage();
        //2、创建一个helper
        MimeMessageHelper messageHelper= null;
        try {
            messageHelper = new MimeMessageHelper(mineMessage,true);
            //添加复杂的邮件信息
            messageHelper.setText(html,true);
            messageHelper.setSubject("感谢信");
            messageHelper.setTo(apply.getUser_email());  //收件人
            messageHelper.setFrom("1973195349@qq.com");//发送人
            mailSender.send(mineMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
