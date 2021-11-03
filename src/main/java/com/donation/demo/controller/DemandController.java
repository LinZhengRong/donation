package com.donation.demo.controller;

import com.auth0.jwt.JWT;
import com.donation.demo.model.*;
import com.donation.demo.service.ApplyService;
import com.donation.demo.service.DemandService;
import com.donation.demo.service.UserService;
import com.donation.demo.util.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@UserLoginToken
public class DemandController {
    @Autowired
    private DemandService demandService;
    @Autowired
    private ApplyService applyService;
    @Autowired
    private UserService userService;
    /*
    @GetMapping("/api/demand/getList")
    @ResponseBody
    public Result getList(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize){
        List<Demand> list = demandService.getList((pageNum-1)*pageSize,pageSize);
        int total = demandService.getTotal();
        Map<String,Object> data=new HashMap<String,Object>();
        data.put("list",list);
        data.put("total",total);
        Result result = new Result(20000,"success",data);
        return result;
    }*/
    /**同上*/

    @PostMapping("/api/demand/getList1/{pageNum}/{limit}")
    @ResponseBody
    public Result getList1(@RequestBody User user,@PathVariable("pageNum") int pageNum, @PathVariable("limit") int pageSize){
        List<Demand> list = demandService.getList(user.getUser_nick(),(pageNum-1)*pageSize,pageSize);
        int total = demandService.getTotal();
        Map<String,Object> data=new HashMap<String,Object>();
        data.put("infoList",list);
        data.put("total",total);
        Result result = new Result(20000,"success",data);
        return result;
    }




    /*获取已通过审核的需求*/
    @PostMapping("/api/demand/getListHasPass/{pageNum}/{limit}")
    @ResponseBody
    public Result getListHasPass(@PathVariable("pageNum") int pageNum, @PathVariable("limit") int pageSize){
        List<Demand> list = demandService.getListHasPass((pageNum-1)*pageSize,pageSize);
        int total = demandService.getTotalHasPass();
        Map<String,Object> data=new HashMap<String,Object>();
        data.put("infoList",list);
        data.put("total",total);
        Result result = new Result(20000,"success",data);
        return result;
    }

    @PostMapping("/api/demand/getMyDemand/{pageNum}/{limit}")
    @ResponseBody
    public Result getMyDemand(@PathVariable("pageNum") int pageNum, @PathVariable("limit") int pageSize, HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        String user_id = JWT.decode(token).getAudience().get(0);
        List<Demand> list = demandService.getMyDemand((pageNum-1)*pageSize,pageSize,Integer.parseInt(user_id));
        int total = demandService.getMyTotal(Integer.parseInt(user_id));
        Map<String,Object> data=new HashMap<String,Object>();
        data.put("infoList",list);
        data.put("total",total);
        Result result = new Result(20000,"success",data);
        return result;
    }
    //获取单条
    @GetMapping("/api/demand/get")
    @ResponseBody
    public Result get(@RequestParam(value = "id") int demand_id){
        Demand demand = demandService.get(demand_id);

        Map<String,Object> data=new HashMap<String,Object>();
        data.put("demand",demand);
        //查询是否有人申请该条需求的捐助资格
        if(demandService.getApplyPeople(demand_id,0,1) != null){
            data.put("hasApply",true);
            data.put("realTotal",applyService.getRealTotal(demand_id));
        }else{
            data.put("hasApply",false);
            data.put("realTotal",0);
        }
        Result result = new Result(20000,"success",data);
        return result;
    }

    //获取需求对应的捐助列表
    @PostMapping("/api/demand/getApplys/{demand_id}/{pageNum}/{limit}")
    @ResponseBody
    public Result getApplys(@PathVariable("demand_id") int demand_id,@PathVariable("pageNum") int pageNum, @PathVariable("limit") int pageSize){
        List<Apply> applys = demandService.getApplyPeople(demand_id,(pageNum-1)*pageSize,pageSize).getApplyList();
        Map<String,Object> data=new HashMap<String,Object>();
        data.put("infoList",applys);
        Result result = new Result(20000,"success",data);
        return result;
    }

    @GetMapping("/api/demand/getType")
    @ResponseBody
    public Result getType(){
        List<Type> types = demandService.getType();
        Map<String,Object> data=new HashMap<String,Object>();
        data.put("types",types);
        Result result = new Result(20000,"success",data);
        return result;
    }

    @PostMapping("/api/demand/uploadImage")
    @ResponseBody
    public Image uploadImage(@RequestParam("file") MultipartFile multipartFile) {
        Image image = demandService.uploadImage(multipartFile);
        return image;
    }

    @PostMapping("/api/demand/addDemand")
    @ResponseBody
    public Result addDemand(@RequestBody Demand demand,HttpServletRequest httpServletRequest) {
        Result result = new Result();
        String token = httpServletRequest.getHeader("token");// 从http请求头中取出 token
        String user_id = JWT.decode(token).getAudience().get(0);
        // 限制需求方同时最多存在3条正在进行的需求
        if(demandService.getPersonNum(Integer.parseInt(user_id)) >= 3){
            return new Result(20000,"toMuch",null);
        }
        String[] address = demand.getAddress_arr();
        Address addr = new Address();
        addr.setAddress_province(address[0]);
        addr.setAddress_city(address[1]);
        addr.setAddress_district(address[2]);
        addr.setAddress_detail(address[3]);
        if(demandService.insertAddress(addr)){
            //地址表新插入的id
            demand.setDemand_destination(addr.getAddress_id());
            Picture picture = new Picture();
            picture.setPic_path(demand.getDemand_pic_name());
            picture.setPic_upload_time(new Date());
            if(demandService.insertPic(picture)){
                //图片表新插入的id
                demand.setDemand_pic(picture.getPic_id());
                demand.setDemand_time(new Date());
                demand.setDemand_publisher(Integer.parseInt(user_id));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date d  = null;
                try {
                    d  = sdf.parse(demand.getEnd_time());
                    demand.setDemand_end_time(d);
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(demandService.addDemand(demand)){
                    result = new Result(20000,"success",null);
                }else{
                    result = new Result(20000,"error",null);
                }
            }
        }
          return result;
    }

    @PostMapping("/api/demand/checkDemand")
    @ResponseBody
    public Result checkDemand(@RequestBody Demand demand){
        Result result = new Result();
        demand.setDemand_check_time(new Date());
        if(demandService.checkDemand(demand)){
            result = new Result(20000,"success",null);
        }
        return result;
    }

    @GetMapping("/api/demand/updateTime")
    @ResponseBody
    public Result updateTime(@RequestParam("id") int demand_id,@RequestParam("date") String date){
        Result result = new Result();
        Demand demand = new Demand();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d  = null;
        try {
            d  = sdf.parse(date);
            demand.setDemand_end_time(d);
            demand.setDemand_id(demand_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(demandService.updateTime(demand)){
            result = new Result(20000,"success",null);
        }
        return result;
    }

    /*获取需求物资的列表*/
    @PostMapping("/api/demand/getPics/{pageNum}/{limit}")
    @ResponseBody
    public Result getPics(@RequestBody Demand demand,@PathVariable("pageNum") int pageNum, @PathVariable("limit") int pageSize){
        List<Demand> list = demandService.getPics(demand,(pageNum-1)*pageSize,pageSize);
        int total = demandService.getListTotal(demand);
        Map<String,Object> data=new HashMap<String,Object>();
        data.put("infoList",list);
        data.put("total",total);
        Result result = new Result(20000,"success",data);
        return result;
    }

    /*获取需求物资的*/
    @GetMapping("/api/demand/getPic")
    @ResponseBody
    public Result getPics(@RequestParam("id") int id){
        Demand info = demandService.getPic(id);
        Map<String,Object> data=new HashMap<String,Object>();
        data.put("info",info);
        Result result = new Result(20000,"success",data);
        return result;
    }





    /*结束需求*/
    @GetMapping("/api/demand/finish")
    @ResponseBody
    public Result finsih(@RequestParam("id") int id){
        Result result = new Result();
        Demand demand = demandService.get(id);
        demand.setDemand_status(9);
        if(demandService.updateDemand(demand)){
            result = new Result(20000,"success",null);
        }else{
            result = new Result(20000,"error",null);
        }
        return result;
    }


    /*修改物资信息*/
    @PostMapping("/api/demand/updatePicAndOthers/{flag}")
    @ResponseBody
    public Result updatePicAndOthers(@RequestBody Demand demand,@PathVariable(name="flag") int flag){
        Result result = new Result();
        if(flag == 1){
            Picture picture = new Picture();
            picture.setPic_path(demand.getDemand_pic_name());
            picture.setPic_upload_time(new Date());
            demandService.insertPic(picture);
            //图片表新插入的id
            demand.setDemand_pic(picture.getPic_id());
            if(demandService.updateDemand(demand)){
                result = new Result(20000,"success",null);
            }else{
                result = new Result(20000,"error",null);
            }
        }else{
            if(demandService.updateDemand(demand)){
                result = new Result(20000,"success",null);
            }else{
                result = new Result(20000,"error",null);
            }
        }
        return result;
    }





}
