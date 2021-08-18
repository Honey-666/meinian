package com.atguigu.dao;

import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.TravelGroup;

import java.util.List;

public interface TravelGroupMapper {


    void insert(TravelGroup travelGroup);

    List<TravelGroup> findPage(QueryPageBean queryPageBean);

    void delete(Integer id);

    TravelGroup findById(Integer id);

    void edit(TravelGroup travelGroup);

    List<TravelGroup> findAll();

    List<TravelGroup> findTravelGroupBySetmealId(Integer id);

}
