package com.atguigu.dao;

import com.atguigu.pojo.TravelGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TravelGroupTravelItemMapper {


    Long count(Integer id);


    void batchInsert(@Param("travelItemIds") Integer[] travelItemIds, @Param("travelGroupId") Integer travelGroupId);

    List<Integer> findTravelItemIdByTravelgroupId(Integer id);

    void delete(Integer id);
}
