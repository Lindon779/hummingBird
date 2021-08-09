package com.example.demo.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class compareTime {
    public static long compareTime(String time) throws ParseException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        simpleFormat.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        String now = getCurrentTime.getTime();
        Date fromDate = simpleFormat.parse(now);
        Date toDate = simpleFormat.parse(time);
        long i = fromDate.getTime() - toDate.getTime();
        return i/1000/60;
    }

    public static String showTime() throws ParseException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        simpleFormat.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        return getCurrentTime.getTime();
    }
}
