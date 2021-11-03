package com.donation.demo.controller;

import com.auth0.jwt.JWT;
import com.donation.demo.model.Picture;
import com.donation.demo.model.Result;
import com.donation.demo.model.User;
import com.donation.demo.service.DemandService;
import com.donation.demo.service.TokenService;
import com.donation.demo.service.UserService;
import com.donation.demo.util.Md5Tool;
import com.donation.demo.util.Md5Util;
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
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private DemandService demandService;




    @PostMapping("/api/user/login")
    @ResponseBody
    public Result doLogin(@RequestBody User user) throws Exception {
        Result result = new Result();
        result.setCode(20000);
        Map<String,Object> data=new HashMap<String,Object>();
        //查找用户
        User userForBase=userService.hasUserByName(user.getUser_name());
        if(userForBase==null){
            result.setMessage("登录失败,用户不存在");
            result.setCode(50008);
            return result;
        }else{
            String md5Password  = Md5Util.md5(user.getUser_password(), Md5Tool.KEY);
            user.setUser_password(md5Password);
            if (!userForBase.getUser_password().equals(user.getUser_password())){
                result.setMessage("登录失败,密码错误");
                result.setCode(50008);
                return result;
            }
             else if(userForBase.getUser_state() == 0){
                result.setMessage("此账号已被禁用");
                result.setCode(50008);
                return result;
            }else {
                //验证成功
                String token = tokenService.getToken(userForBase);
                data.put("authority",userForBase.getUser_authority());
                data.put("token",token);
                result.setData(data);
                result.setMessage("success");
                return result;
            }
        }
    }

    @UserLoginToken
    @GetMapping("/api/user/info")
    @ResponseBody
    public Result getUserInfo(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        String user_id = JWT.decode(token).getAudience().get(0);
        User user = userService.hasUser(Integer.parseInt(user_id));
        Map<String,Object> data=new HashMap<String,Object>();
        data.put("userInfo",user);
        Result result = new Result(20000,"success",data);
        return result;
    }

    @UserLoginToken
    @GetMapping("/api/user/getUser")
    @ResponseBody
    public Result getUser(@RequestParam("id") int id){
        User user = userService.hasUser(id);
        Map<String,Object> data=new HashMap<String,Object>();
        data.put("userInfo",user);
        Result result = new Result(20000,"success",data);
        return result;
    }

    @UserLoginToken
    @PostMapping("/api/user/list/{pageNum}/{limit}")
    @ResponseBody
    public Result getUserlist(@RequestBody User user,@PathVariable("pageNum") int pageNum, @PathVariable("limit") int pageSize){
        List<User> userList = userService.getUserList(user,(pageNum-1)*pageSize,pageSize);
        int total = userService.getUserListTotal(user);
        Map<String,Object> data=new HashMap<String,Object>();
        data.put("infoList",userList);
        data.put("total",total);
        Result result = new Result(20000,"success",data);
        return result;
    }

    @UserLoginToken
    @PostMapping("/api/user/update")
    @ResponseBody
    public Result updateUser(@RequestBody User user){
        //处理证件资质
        if(user.getImg() != null && user.getUser_type() == 1){
            Picture picture = new Picture();
            picture.setPic_path(user.getImg());
            picture.setPic_upload_time(new Date());
            if(demandService.insertPic(picture)){
                //图片表新插入的id
                user.setUser_credential(picture.getPic_id());
            }
        }
        boolean isSuccess =  userService.updateUser(user);
        String message = isSuccess?"success":"error";
        Result result = new Result(20000,message,null);
        return result;
    }


    @UserLoginToken
    @PostMapping("/api/user/changePwd")
    @ResponseBody
    public Result changePwd(@RequestBody User user,HttpServletRequest httpServletRequest) throws Exception {
        Result result = new Result();
        String token = httpServletRequest.getHeader("token");
        String user_id = JWT.decode(token).getAudience().get(0);
        User oldUser = userService.hasUser(Integer.parseInt(user_id));
        //对用户输入的旧密码进行加密
        String md5Password  = Md5Util.md5(user.getUser_password(), Md5Tool.KEY);
        user.setUser_password(md5Password);
        //判断新旧密码
        if(!oldUser.getUser_password().equals(user.getUser_password())){
            result = new Result(20000,"pwdError",null);
            return result;
        }
        user.setUser_id(Integer.parseInt(user_id));
        //对新密码进行加密
        String md5PasswordNew  = Md5Util.md5(user.getUser_new_password(), Md5Tool.KEY);
        user.setUser_password(md5PasswordNew);
        if(userService.updateUser(user)){
            result = new Result(20000,"success",null);
        }else {
            result = new Result(20000,"error",null);
        }
        return result;
    }


    @UserLoginToken
    @PostMapping("/api/user/forbidden")
    @ResponseBody
    public Result forbidden(@RequestBody User user){
        Result result = new Result();
        if(user.getUser_state() == 1){
            user.setUser_state(0);
        }else{
            user.setUser_state(1);
        }
        if(userService.updateState(user)){
            result = new Result(20000,"success",null);
        }else{
            result = new Result(20000,"fail",null);
        }

        return result;
    }



    @PostMapping("/api/user/logout")
    @ResponseBody
    public Result userLogout(){
        Result result = new Result(20000,"success",null);
        return result;
    }

    @PostMapping("/api/user/resetPwd")
    @ResponseBody
    public Result resetPwd(@RequestBody  User user) throws Exception {
        Result result = new Result();
        User currentUser = userService.hasUser(user.getUser_id());
        String ID = currentUser.getUser_ID_number();

        ID = ID.substring(ID.length()-6);
        //加密

        String md5Password  = Md5Util.md5(ID, Md5Tool.KEY);
        currentUser.setUser_password(md5Password);
        if(userService.updateUser(currentUser)){
            result = new Result(20000,"success",null);
        }else {
            result = new Result(20000, "error", null);
        }
            return result;
        }


    @PostMapping("/api/user/reg")
    @ResponseBody
    public Result userReg(@RequestBody User user){
        Result result = new Result();
        User oldUser = userService.getByCondition(user);
        if(oldUser != null){
            result = new Result(20000,"NameRepeat",null);
            return result;
        }else{
            //处理证件资质
            if(user.getImg() != null && user.getUser_type() == 1){
                Picture picture = new Picture();
                picture.setPic_path(user.getImg());
                picture.setPic_upload_time(new Date());
                if(demandService.insertPic(picture)){
                    //图片表新插入的id
                    user.setUser_credential(picture.getPic_id());
                }
            }
            String md5Password = null;
            try {
                md5Password = Md5Util.md5(user.getUser_password(), Md5Tool.KEY);
            } catch (Exception e) {
                e.printStackTrace();
            }
            user.setUser_password(md5Password);
            if(userService.reg(user)) {
                result = new Result(20000,"success",null);
            }else{
                result = new Result(20000,"error",null);
            }
        }
        return result;
    }
}
