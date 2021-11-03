package com.donation.demo.controller;

import com.donation.demo.model.Result;
import com.donation.demo.model.Type;
import com.donation.demo.service.TypeService;
import com.donation.demo.util.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@UserLoginToken
public class TypeController {
    @Autowired
    private TypeService typeService;
    @PostMapping("/api/type/list/{pageNum}/{limit}")
    @ResponseBody
    public Result getList(@RequestBody Type type, @PathVariable("pageNum") int pageNum, @PathVariable("limit") int limit){
        List<Type> list = typeService.list(type,(pageNum-1)*limit,limit);
        int total = typeService.getCount(type);
        Map<String,Object> data=new HashMap<String,Object>();
        data.put("infoList",list);
        data.put("total",total);
        Result result = new Result(20000,"success",data);
        return result;
    }

    @PostMapping("/api/type/updateOne")
    @ResponseBody
    public Result updateOne(@RequestBody Type type){
        Result result = new Result();
       if(typeService.updateOne(type)){
           result = new Result(20000,"success",null);
       }else{
           result = new Result(20000,"error",null);
       }
        return result;
    }
    @PostMapping("/api/type/insertOne")
    @ResponseBody
    public Result insertOne(@RequestBody Type type){
        Result result = new Result();
        if(typeService.insertOne(type)){
            result = new Result(20000,"success",null);
        }else{
            result = new Result(20000,"error",null);
        }
        return result;
    }

    @GetMapping("/api/type/get")
    @ResponseBody
    public Result getOne(@RequestParam("id") int id){
        Result result = new Result();
        Type type = typeService.get(id);
        Map<String,Object> data=new HashMap<String,Object>();
        data.put("type",type);
        result = new Result(20000,"success",data);
        return result;
    }

    @GetMapping("/api/type/del")
    @ResponseBody
    public Result delOne(@RequestParam("id") int id){
        Result result = new Result();
        if(typeService.del(id)){
            result = new Result(20000,"success",null);
        }else{
            result = new Result(20000,"error",null);
        }
        return result;
    }

}
