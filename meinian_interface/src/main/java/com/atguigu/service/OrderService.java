package com.atguigu.service;

import com.atguigu.entity.Result;
import com.atguigu.pojo.Order;

import java.util.Map;

public interface OrderService {
    Result submit(Map<String, String> map) throws Exception;

    Map<String, Object> findById(Integer id);
}
