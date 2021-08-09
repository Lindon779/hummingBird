package com.example.demo;

import com.example.demo.tools.getCurrentTime;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@SpringBootTest
class ResumeApplicationTests {

    @Test
    void contextLoads() throws ParseException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        simpleFormat.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        String time = "2021-03-27 11:50:44.000000";
        String now = getCurrentTime.getTime();
//        int i = time.compareTo(now);
//        System.out.println(i);
        Date fromDate = simpleFormat.parse(now);
        Date toDate = simpleFormat.parse(time);
        long i = fromDate.getTime() - toDate.getTime();
        System.out.println(i/1000/60);
    }

}
