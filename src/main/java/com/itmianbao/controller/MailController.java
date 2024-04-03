package com.itmianbao.controller;

import com.itmianbao.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3001")
@RequestMapping("/mail")
/**
 * 对于邮件发送的控制类
 */
public class MailController {
    @Autowired
    JavaMailSender javaMailSender;


    @GetMapping("/comment")
    public Result sendCommentMail(){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("1623666966@qq.com");
        message.setTo("1623666966@qq.com");
        message.setSubject("测试：这是一个测试demo");
        message.setText("我是测试demo！！！");
        javaMailSender.send(message);
        return Result.success();
    }
}
