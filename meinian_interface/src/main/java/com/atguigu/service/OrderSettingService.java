package com.atguigu.service;

import com.atguigu.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

public interface OrderSettingService {
    void inset(List<OrderSetting> list);

    List<Map<String, Integer>> queryData(String data);

    void edit(OrderSetting orderSetting);
}
