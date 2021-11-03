package com.donation.demo.controller;

import com.auth0.jwt.JWT;
import com.donation.demo.model.Feedback;
import com.donation.demo.model.Result;
import com.donation.demo.service.FeedbackService;
import com.donation.demo.util.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UserLoginToken
@Controller
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/api/feedback/getList/{pageNum}/{limit}")
    @ResponseBody
    public Result getList(@RequestBody Feedback feedback,@PathVariable("pageNum") int pageNum, @PathVariable("limit") int limit){
        List<Feedback> list = feedbackService.getFeedbackList((pageNum-1)*limit,limit);
        int total = feedbackService.getTotal();
        Map<String,Object> data=new HashMap<String,Object>();
        data.put("infoList",list);
        data.put("total",total);
        Result result = new Result(20000,"success",data);
        return result;
    }


    @PostMapping("/api/feedback/add")
    @ResponseBody
    public Result add(@RequestBody Feedback feedback, HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        String user_id = JWT.decode(token).getAudience().get(0);
        feedback.setFeedback_senderID(Integer.parseInt(user_id));
        feedback.setFeedback_time(new Date());
        Result result = new Result();
        if(feedbackService.add(feedback)){
            result = new Result(20000,"success",null);
        }else{
            result = new Result(20000,"fail",null);
        }
        return result;
    }


    @GetMapping("/api/feedback/delItem")
    @ResponseBody
    public Result delItem(@RequestParam("feedback_id") int id ) {
        boolean isSuccess = feedbackService.delItem(id);
        Result result = new Result(20000,"success",isSuccess);
        return result;
    }
}
