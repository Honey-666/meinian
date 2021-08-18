package com.atguigu.dao;

import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealMapper {
    void add(Setmeal setmeal);

    List<Setmeal> findPage(QueryPageBean queryPageBean);

    void delete(Integer id);

    Setmeal findById(Integer id);

    void edit(Setmeal setmeal);

    List<Setmeal> getSetmealAll();

    Setmeal findSetmealById(Integer id);

    List<Map<String, Object>> findSetmenlCount();
}
