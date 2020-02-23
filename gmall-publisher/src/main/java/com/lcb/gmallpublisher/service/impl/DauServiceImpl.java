package com.lcb.gmallpublisher.service.impl;

import com.lcb.gmallpublisher.mapper.DauMapper;
import com.lcb.gmallpublisher.service.DauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DauServiceImpl implements DauService {

    @Autowired
    private DauMapper dauMapper;

    @Override
    public int getDauTotal(String date) {
        return dauMapper.selectDauTotal(date);
    }

    @Override
    public Map getDauHours(String date) {
        //把List<map>转换为一个Map,因为一个map中只有两个键值对，一个“LH”-> 一个“CT”
        List<Map> maps = dauMapper.selectDauTotalHourMap(date);
        HashMap<String, Long> res = new HashMap<>();
        for(Map map : maps){
            res.put((String)map.get("LH"),(Long)map.get("CT"));
        }
        return res;
    }
}
