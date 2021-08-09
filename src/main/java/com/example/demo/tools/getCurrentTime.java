package com.example.demo.tools;

import java.util.TimeZone;

public class getCurrentTime {

    public static String getTime() {
        java.util.Locale locale=java.util.Locale.CHINA;
        String pattern = "yyyy-MM-dd kk:mm:ss";
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(pattern,locale);
        df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        java.util.Date date = new java.util.Date();
        String bjTime = df.format(date);
        return bjTime;
    }

}
