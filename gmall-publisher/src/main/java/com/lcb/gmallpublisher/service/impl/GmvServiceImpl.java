package com.lcb.gmallpublisher.service.impl;

import com.lcb.gmallpublisher.mapper.GmvMapper;
import com.lcb.gmallpublisher.service.GmvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GmvServiceImpl implements GmvService{

    @Autowired
    private GmvMapper gmvMapper;

    @Override
    public Double getOrderAmount(String date) {
        return gmvMapper.selectOrderAmountTotal(date);
    }

    @Override
    public Map getOrderAmountHour(String date) {
        List<Map> mapList = gmvMapper.selectOrderAmountHourMap(date);
        Map res = new HashMap();
        for (Map map : mapList) {
            res.put(map.get("CREATE_HOUR"), map.get("SUM_AMOUNT"));
        }
        return res;
    }
}
