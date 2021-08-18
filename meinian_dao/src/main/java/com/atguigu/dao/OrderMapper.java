package com.atguigu.dao;

import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderMapper {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    Map<String, Object> findById(Integer id);

    int getTodayOrderNumber(String today);

    int getTodayVisitsNumber(String today);

    int getThisWeekOrderNumber(@Param("thisWeekMonday") String thisWeekMonday, @Param("sunday") String sunday);

    int getThisWeekVisitsNumber(@Param("thisWeekMonday") String thisWeekMonday, @Param("sunday") String sunday);

    int getThisMonthOrderNumber(@Param("firstDayThisMonth") String firstDayThisMonth, @Param("monthLast") String monthLast);

    int getThisMonthVisitsNumber(@Param("firstDayThisMonth") String firstDayThisMonth, @Param("monthLast") String monthLast);

    List<Map<String, Object>> findHotSetmeal();
}