package com.donation.demo.controller;

import com.auth0.jwt.JWT;
import com.donation.demo.model.*;
import com.donation.demo.service.ActivityService;
import com.donation.demo.service.DemandService;
import com.donation.demo.service.UserService;
import com.donation.demo.util.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UserLoginToken
@Controller
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private DemandService demandService;
    @Autowired
    private UserService userService;
    @PostMapping("/api/activity/list/{pageNum}/{limit}")
    @ResponseBody
    public Result getList(@RequestBody Activity activity,@PathVariable("pageNum") int pageNum, @PathVariable("limit") int limit){
        List<Activity> list = activityService.getList(activity,(pageNum-1)*limit,limit);
        int total = activityService.getCount();
        Map<String,Object> data=new HashMap<String,Object>();
        data.put("infoList",list);
        data.put("total",total);
        Result result = new Result(20000,"success",data);
        return result;
    }

    @GetMapping("/api/activity/myList/{pageNum}/{limit}")
    @ResponseBody
    public Result getMyList(HttpServletRequest httpServletRequest,@PathVariable("pageNum") int pageNum, @PathVariable("limit") int limit){
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        String user_id = JWT.decode(token).getAudience().get(0);
        List<Activity> list = activityService.getMyActivity(Integer.parseInt(user_id),(pageNum-1)*limit,limit);
        int total = activityService.getCount();
        Map<String,Object> data=new HashMap<String,Object>();
        data.put("infoList",list);
        data.put("total",total);
        Result result = new Result(20000,"success",data);
        return result;
    }


    @GetMapping("/api/activity/get")
    @ResponseBody
    public Result get(@RequestParam("id") int activity_id){
        Activity activity = activityService.get(activity_id);
        Map<String,Object> data=new HashMap<String,Object>();
        data.put("info",activity);
        Result result = new Result(20000,"success",data);
        return result;
    }

    @GetMapping("/api/activity/join/{num}")
    @ResponseBody
    public Result join(@RequestParam("id") int id,@PathVariable("num") int num, HttpServletRequest httpServletRequest){
        Result result = new Result();
        String token = httpServletRequest.getHeader("token");
        String user_id = JWT.decode(token).getAudience().get(0);
        User user = userService.hasUser(Integer.parseInt(user_id));
        ActivityAndUser aau = new ActivityAndUser();
        aau.setPerson_id(Integer.parseInt(user_id));
        aau.setActivity_id(id);
        if(activityService.checkHasJoin(aau)!=null){
            //用户已报名过
            result = new Result(20000,"hasJoin",null);
            return result;
        }else{
            activityService.updateOne(id,num);
            //新增数据
            ActivityAndUser activityAndUser = new ActivityAndUser();
            activityAndUser.setActivity_id(id);
            activityAndUser.setPerson_id(Integer.parseInt(user_id));
            activityAndUser.setTime(new Date());
            if(user.getUser_type() == 0){
                num = 1;
            }
            activityAndUser.setTeam_num(num);
            if(activityService.joinActivity(activityAndUser)){
                result = new Result(20000,"success",null);
            }else{
                result = new Result(20000,"error",null);
            }
        }
        return result;
    }


    @GetMapping("/api/activity/getPeople")
    @ResponseBody
    public Result getPeople(@RequestParam("id") int id){
        List<ActivityAndUser> list = activityService.getPeople(id);
        Map<String,Object> data=new HashMap<String,Object>();
        data.put("list",list);
        Result result = new Result(20000,"success",data);
        return result;
    }


    @GetMapping("/api/activity/getType")
    @ResponseBody
    public Result getType(){
        List<ActivityType> type = activityService.getType();
        Map<String,Object> data=new HashMap<String,Object>();
        data.put("type",type);
        Result result = new Result(20000,"success",data);
        return result;
    }

    @PostMapping("/api/activity/add")
    @ResponseBody
    public Result add(@RequestBody Activity activity,HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        String user_id = JWT.decode(token).getAudience().get(0);
        Result result = new Result();
        String[] address = activity.getAddress_arr();
        Address addr = new Address();
        addr.setAddress_province(address[0]);
        addr.setAddress_city(address[1]);
        addr.setAddress_district(address[2]);
        addr.setAddress_detail(address[3]);
        //添加地址
        if(demandService.insertAddress(addr)){
            //地址表新插入的id
            activity.setActivity_area(addr.getAddress_id());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date start  = null;
            Date end = null;
            try {
                start  = sdf.parse(activity.getStart());
                end = sdf.parse(activity.getEnd());
                activity.setActivity_start_time(start);
                activity.setActivity_end_time(end);
                activity.setActivity_publish_time(new Date());
                activity.setActivity_publisher(Integer.parseInt(user_id));
            }catch (Exception e){
                e.printStackTrace();
            }

            if(activityService.add(activity)){
                result = new Result(20000,"success",null);
            }else{
                result = new Result(20000,"fail",null);
            }
        }
        return result;
    }

    @PostMapping("/api/activity/update")
    @ResponseBody
    public Result update(@RequestBody Activity activity,HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        String user_id = JWT.decode(token).getAudience().get(0);
        Result result = new Result();
        String[] address = activity.getAddress_arr();
        Address addr = new Address();
        addr.setAddress_id(activity.getAddress().getAddress_id());
        addr.setAddress_province(address[0]);
        addr.setAddress_city(address[1]);
        addr.setAddress_district(address[2]);
        addr.setAddress_detail(address[3]);
        //修改地址
        if(demandService.updateAddress(addr)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date start  = null;
            Date end = null;
            try {
                start  = sdf.parse(activity.getStart());
                end = sdf.parse(activity.getEnd());
                activity.setActivity_start_time(start);
                activity.setActivity_end_time(end);
                activity.setActivity_update_time(new Date());
                activity.setActivity_update_by(Integer.parseInt(user_id));
                activity.setActivity_team_num(activity.getActivity_team_num());
            }catch (Exception e){
                e.printStackTrace();
            }

            if(activityService.update(activity)){
                result = new Result(20000,"success",null);
            }else{
                result = new Result(20000,"fail",null);
            }
        }
        return result;
    }





    @GetMapping("/api/activity/delItem")
    @ResponseBody
    public Result delItem(@RequestParam("activity_id") int id ) {
        boolean isSuccess = activityService.delItem(id);
        Result result = new Result(20000,"success",isSuccess);
        return result;
    }
}
