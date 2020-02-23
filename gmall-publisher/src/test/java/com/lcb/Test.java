package com.lcb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    public static void main(String[] args) {
        //获取昨日的分时数据
        String yestodayDate = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //根据格式解析为日期
            Date todayDate = sdf.parse("2020-03-01");
            //获取当前日期的时间戳
            long ts = todayDate.getTime();
            //减去一天的时间戳
            ts -= 24*60*60*1000;
            //按照格式把时间戳转换为字符串
            yestodayDate = sdf.format(ts);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(yestodayDate);
    }
}
