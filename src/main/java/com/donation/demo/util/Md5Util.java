package com.donation.demo.util;

import org.springframework.util.DigestUtils;
public class Md5Util {
    public static String md5(String text,String key)throws Exception{
        //加密后的字符串
        String s = DigestUtils.md5DigestAsHex((text + key).getBytes());
        return s;
    }
    public static boolean verify(String text,String key,String md5)throws Exception{
        //根据传入的秘钥进行验证
        String md5Text=md5(text, key);
        return md5Text.equalsIgnoreCase(md5);
    }

    public static boolean verify(String pwd,String dpwd){
        String s = null;
        try {
            s = md5(pwd, Md5Tool.KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s.equals(dpwd);
    }
}
