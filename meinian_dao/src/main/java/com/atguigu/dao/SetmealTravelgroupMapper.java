package com.atguigu.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SetmealTravelgroupMapper {

    void insert(@Param("travelgroupIds") Integer[] travelgroupIds, @Param("id") Integer id);

    Long count(Integer id);

    List<Integer> findSetmealIdByTravelgroupId(Integer id);

    void delete(Integer id);
}
