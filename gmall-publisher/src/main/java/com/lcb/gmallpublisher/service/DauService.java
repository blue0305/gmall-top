package com.lcb.gmallpublisher.service;

import org.springframework.stereotype.Service;

import java.util.Map;

public interface DauService {

    public int getDauTotal(String date);
    public Map getDauHours(String date);
}
