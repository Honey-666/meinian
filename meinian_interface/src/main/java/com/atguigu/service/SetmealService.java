package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealService {
    void add(Integer[] travelgroupIds, Setmeal setmeal);

    PageResult findPage(QueryPageBean queryPageBean);

    void delete(Integer id);

    Setmeal findById(Integer id);

    List<Integer> findSetmealIdByTravelgroupId(Integer id);

    void edit(Integer[] travelgroupIds, Setmeal setmeal);

    List<Setmeal> getSetmealAll();

    Setmeal findSetmealById(Integer id);

    List<Map<String, Object>> findSetmenlCount();
}
