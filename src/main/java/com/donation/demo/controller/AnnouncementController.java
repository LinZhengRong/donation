package com.donation.demo.controller;

import com.donation.demo.model.Image;
import com.donation.demo.model.Result;
import com.donation.demo.model.announcement;
import com.donation.demo.service.AnnouncementService;
import com.donation.demo.util.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    @PostMapping("/api/announcement/getList/{pageNum}/{limit}")
    @ResponseBody
    public Result getList(@RequestBody announcement announcement,@PathVariable("pageNum") int pageNum, @PathVariable("limit") int limit){
        List<announcement> list = announcementService.getInfo(announcement,(pageNum-1)*limit,limit);
        int total = announcementService.getCount(announcement);
        Map<String,Object> data=new HashMap<String,Object>();
        data.put("infoList",list);
        data.put("total",total);
        Result result = new Result(20000,"success",data);
        return result;
    }

    @UserLoginToken
    @PostMapping("/api/announcement/add")
    @ResponseBody
    public Result addArticle(@RequestBody announcement announcement) {
        announcement.setAnnouncement_publisher(1);
        announcement.setAnnouncement_time(new Date());
        boolean isSuccess = announcementService.addAnnouncement(announcement);
        Result result = new Result(20000,"success",isSuccess);
        return result;
    }

    @PostMapping("/uploadImage")
    @ResponseBody
    public Image uploadImage(@RequestParam("file") MultipartFile multipartFile) {
        Image image = announcementService.uploadImage(multipartFile);
        return image;
    }


    @UserLoginToken
    @GetMapping("/api/announcement/delItem")
    @ResponseBody
    public Result delItem(@RequestParam("announcement_id") int id ) {
        boolean isSuccess = announcementService.delItem(id);
        Result result = new Result(20000,"success",isSuccess);
        return result;
    }

    @UserLoginToken
    @GetMapping("/api/announcement/delMany")
    @ResponseBody
    public Result delMany(@RequestParam("ids") String ids ) {
        String[] idArray = ids.split(",");
        for (int i = 0; i < idArray.length; i++) {
            announcementService.delItem(Integer.parseInt(idArray[i]));
        }
        return new Result(20000,"success",null);
    }


    @UserLoginToken
    @GetMapping("/api/announcement/get")
    @ResponseBody
    public Result get(@RequestParam("id") int id ) {
        announcement announcement = announcementService.get(id);
        Map<String,Object> data=new HashMap<String,Object>();
        data.put("info",announcement);
        Result result = new Result(20000,"success",data);
        return result;
    }

    @UserLoginToken
    @PostMapping("/api/announcement/updateOne")
    @ResponseBody
    public Result get(@RequestBody announcement announcement ) {
        Result result = new Result();
        announcement.setAnnouncement_time(new Date());
        if(announcementService.updateOne(announcement)){
            result = new Result(20000,"success",null);
        }else{
            result = new Result(20000,"fail",null);
        }
        return result;
    }
}
