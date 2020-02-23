package com.lcb.gmallpublisher.controller;

import com.alibaba.fastjson.JSON;
import com.lcb.gmallpublisher.service.DauService;
import com.lcb.gmallpublisher.service.GmvService;
import com.lcb.gmallpublisher.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PublisherController {

    @Autowired
    private DauService dauService;

    @Autowired
    private GmvService gmvService;

    @GetMapping("realtime-total")
    public String getRealTimeTotal(@RequestParam("date") String date){

        //获取总数
        int dauTotal = dauService.getDauTotal(date);

        //创建集合存放json对象
        ArrayList<Map> res = new ArrayList<Map>();

        //创建map存放日活数据
        HashMap<String, Object> dauMap = new HashMap<>();
        dauMap.put("id","dau");
        dauMap.put("name","新增日活");
        dauMap.put("value",dauTotal);

        //创建map存放新增设备数据
        HashMap<String, Object> newMidMap = new HashMap<>();
        newMidMap.put("id","new_mid");
        newMidMap.put("name","新增设备");
        newMidMap.put("value","233");

        //创建map存放GMV数据
        Map orderAmountMap=new HashMap();
        orderAmountMap.put("id","order_amount");
        orderAmountMap.put("name","新增交易额");
        Double orderAmount = gmvService.getOrderAmount(date);
        orderAmountMap.put("value",orderAmount);

        //将map数据放入集合中
        res.add(dauMap);
        res.add(newMidMap);
        res.add(orderAmountMap);

        //将集合转换为json字符串返回
        return JSON.toJSONString(res);
    }

    @GetMapping("realtime-hours")
    public String getRealtimeHours(@RequestParam("id") String id,@RequestParam("date") String date){

        //创建Map集合来放昨天和今天的数据
        HashMap<String, Map> res = new HashMap<>();

        //获取昨天的日期
        String yesterdayDate = DateUtils.getYesterday(date);

        Map today = null;
        Map yesterday = null;

        if("dau".equals(id)){
            //获取昨天和今日分时数据
            today = dauService.getDauHours(date);
            yesterday = dauService.getDauHours(yesterdayDate);
        }else if("order_amount".equals(id)){
            //获取昨天和今天的gmv
            today = gmvService.getOrderAmountHour(date);
            yesterday = gmvService.getOrderAmountHour(yesterdayDate);
        }

        res.put("today",today);
        res.put("yesterday",yesterday);

        //返回json字符串
        return JSON.toJSONString(res);
    }
}
