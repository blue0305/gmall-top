package com.lcb.gmallpublisher.service;

import java.util.Map;

public interface GmvService {
    public Double getOrderAmount(String date);

    public Map getOrderAmountHour(String date);
}
