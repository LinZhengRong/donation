package com.donation.demo.service.impl;

import com.donation.demo.dao.DemandMapper;
import com.donation.demo.model.*;
import com.donation.demo.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class DemandServiceImpl implements DemandService {
    @Autowired
    private DemandMapper demandMapper;
    @Value("${local.stotage.folder}")
    private String folder;
    @Override
    public List<Demand> getList(String user_nick,int start, int pageSize) {
        return demandMapper.getListByNick(user_nick,start,pageSize);
    }

    @Override
    public List<Demand> getMyDemand(int start, int pageSize, int id) {
        return demandMapper.getList(start,pageSize,id);
    }

    @Override
    public List<Demand> getListHasPass(int start, int pageSize) {
        return demandMapper.getListHasPass(start,pageSize);
    }

    @Override
    public int getTotal() {
        return demandMapper.getTotal(-1);
    }

    @Override
    public int getListTotal(Demand demand) {
        return demandMapper.getListTotal(demand);
    }

    @Override
    public int getMyTotal(int id) {
        return demandMapper.getTotal(id);
    }

    @Override
    public int getTotalHasPass() {
        return demandMapper.getTotalHasPass();
    }

    @Override
    public Demand get(int demand_id) {
        return demandMapper.get(demand_id);
    }

    @Override
    public List<Type> getType() {
        return demandMapper.getType();
    }


    @Override
    public Image uploadImage(MultipartFile multipartFile) {

        // 原文件名
        String originalFilename = multipartFile.getOriginalFilename();
        if (!isImage(originalFilename)) {
            // 不是图片文件
            return Image.error();
        }
        //拼接后的最终文件名称文件名称
        String finalFilename = UUID.randomUUID().toString().replaceAll("-", "") + "-" + originalFilename;
        //获取当前日期作为目录分层（格式：年/月/日）
        String currentDate = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        //把文件保存到指定位置
        String filePath = folder + currentDate + "/" + finalFilename;
        File storagePath = new File(filePath);
        if (!storagePath.getParentFile().exists()) {
            //目录不存在，创建上级目录
            storagePath.getParentFile().mkdirs();
        }
        try {
            //保存图片
            multipartFile.transferTo(storagePath);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        //返回存储路径
        return Image.success("http://localhost:8085/" + currentDate + "/" + finalFilename);
    }

    @Override
    public boolean insertAddress(Address addr) {
        return demandMapper.insertAddress(addr);
    }

    @Override
    public boolean updateAddress(Address address) {
        return demandMapper.updateAddress(address);
    }

    @Override
    public boolean addDemand(Demand demand) {
        return demandMapper.addDemand(demand);
    }

    @Override
    public boolean insertPic(Picture picture) {
        return demandMapper.insertPic(picture);
    }

    @Override
    public boolean checkDemand(Demand demand) {
        return demandMapper.checkDemand(demand);
    }

    @Override
    public Demand getApplyPeople(int demand_id,int start, int pageSize) {
        return demandMapper.getApplyPeople(demand_id,start,pageSize);
    }

    @Override
    public Boolean updateDemand(Demand demand) {
        return demandMapper.updateDemand(demand);
    }

    @Override
    public boolean updateTime(Demand demand) {
        return demandMapper.updateTime(demand);
    }

    @Override
    public List<Demand> getPics(Demand demand,int start, int pageSize) {
        return demandMapper.getPics(demand,start,pageSize);
    }

    @Override
    public Demand getPic(int id) {
        return demandMapper.getPic(id);
    }

    @Override
    public int getPersonNum(int id) {
        return demandMapper.getPersonNum(id);
    }

    @Override
    public boolean finish(Demand demand) {
        return demandMapper.updateDemand(demand);
    }

    @Override
    public List<Demand> findDemandHasChange() {
        return demandMapper.findDemandHasChange();
    }

    @Override
    public boolean updateNum(Demand demand) {
        return demandMapper.updateNum(demand);
    }


    private boolean isImage(String fileName) {
        //获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String[] suffixs = {".jpg", ".jpeg", ".png", ".gif", ".bmp"};
        for (String str : suffixs) {
            if (str.equalsIgnoreCase(suffix)) {
                return true;
            }
        }
        return false;
    }

}
