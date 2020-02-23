package com.lcb.gmallpublisher.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String getYesterday(String today){
        String yesterdayDate = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //根据格式解析为日期
            Date todayDate = sdf.parse(today);
            //获取当前日期的时间戳
            long ts = todayDate.getTime();
            //减去一天的时间戳
            ts -= 24*60*60*1000;
            //按照格式把时间戳转换为字符串
            yesterdayDate = sdf.format(ts);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return yesterdayDate;
    }
}
