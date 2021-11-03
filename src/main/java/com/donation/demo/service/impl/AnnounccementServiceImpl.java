package com.donation.demo.service.impl;

import com.donation.demo.dao.AnnouncementMapper;
import com.donation.demo.model.Image;
import com.donation.demo.model.announcement;
import com.donation.demo.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AnnounccementServiceImpl implements AnnouncementService {
    @Autowired
    private  AnnouncementMapper announcementMapper ;
    @Value("${local.stotage.folder}")
    private String folder;
    @Override
    public List<announcement> getInfo(announcement announcement,int start,int pageSize) {
        return announcementMapper.getInfo(announcement,start,pageSize);
    }

    @Override
    public int getCount(announcement announcement) {
        return announcementMapper.getCount(announcement);
    }

    @Override
    public boolean addAnnouncement(announcement announcement) {
        return announcementMapper.addAnnouncement(announcement);
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
    public boolean delItem(int id) {
        return announcementMapper.delItem(id);
    }

    @Override
    public announcement get(int id) {
        return announcementMapper.get(id);
    }

    @Override
    public boolean updateOne(announcement announcement) {
        return announcementMapper.updateOne(announcement);
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
