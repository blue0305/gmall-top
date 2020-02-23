package com.lcb.gmalllogger.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lcb.GmallConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class Demo {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @RequestMapping("test")
    public String testDemo(){

        return "success";
    }

    @RequestMapping("test2")
    public String testDemo2(@RequestParam("name") String name){

        return name;
    }
    @PostMapping("log")
    public String logger(@RequestParam("logString") String logStr){

        //添加时间戳字段，因为前台即使传了时间，也不能用
        JSONObject jsonObject = JSON.parseObject(logStr);
        jsonObject.put("ts",System.currentTimeMillis());

        String tsJson = jsonObject.toString();
        //使用log4j打印日志到控制台和文件
        log.info(tsJson);
        //使用kafka生产者将数据发送到kafka集群
        if(tsJson.contains("startup")){
            //将数据发送至启动日志主题
            kafkaTemplate.send(GmallConstants.KAFKA_TOPIC_START,tsJson);
        }else{
            //将日志发送至事件日志主题
            kafkaTemplate.send(GmallConstants.KAFKA_TOPIC_EVENT,tsJson);
        }
        return "success";
    }
}
