package com.example.demo.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class SendMail {
    @Autowired
    private IMailService mailService;
    //  设置字符
    public static final char[] chars="1234567890qwertyuiopasdfghjklzxcvbnm".toCharArray();
    //  设置随机数
    public static Random random = new Random();

    public String sendmail(String to) {
        StringBuffer buffer = new StringBuffer();
        int index;   //获取随机chars下标
        for(int i=0;i<6;i++){
            index = random.nextInt(chars.length);  //获取随机chars下标
            buffer.append(chars[index]);
        }
        mailService.sendSimpleMail(to,"【蜂鸟科技】 登录验证","验证码：【"+buffer.toString() + "】 3分钟内有效。悄咪咪告诉你验证码，打死也不要告诉别人哦！");
        return buffer.toString();
    }
}

