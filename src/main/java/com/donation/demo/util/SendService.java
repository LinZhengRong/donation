package com.donation.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class SendService {
    @Autowired
    private SendEmailService sendAttachmentsMail;
    @Value("${spring.mail.username}")
    private String from;
    public void dump(String email,String content) throws Exception{
        String currentDate = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
        sendAttachmentsMail.sendSimpleEmail(from,email,currentDate+
                "公共物资捐助管理系统-通知",content);
    }
}
