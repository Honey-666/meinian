package com.atguigu.dao;

import com.atguigu.pojo.OrderSetting;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OrderSettingMapper {
    Long countBydate(Date orderdate);

    void update(OrderSetting orderSetting);

    void add(OrderSetting orderSetting);

    List<OrderSetting> queryData(String data);


    OrderSetting selectOrderSettingId(String orderDate);

    void updateReservations(@Param("orderDate") String orderDate, @Param("i") int i);
}
