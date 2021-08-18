package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.OrderSettingMapper;
import com.atguigu.pojo.OrderSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private OrderSettingMapper orderSettingMapper;


    @Override
    public void inset(List<OrderSetting> list) {


        for (OrderSetting orderSetting : list) {
            Date orderdate = orderSetting.getOrderdate();

            Long count = orderSettingMapper.countBydate(orderdate);

            if (count > 0){
                //有数据，进行修改
                orderSettingMapper.update(orderSetting);
            }else {
                //没有数据，进行添加
                orderSettingMapper.add(orderSetting);
            }

        }

    }

    /**
     * 获取当月数据到前端页面
     * @param data
     * @return
     */
    @Override
    public List<Map<String, Integer>> queryData(String data) {
        String likedata = data+"-%";
       List<OrderSetting> list =  orderSettingMapper.queryData(likedata);
        List<Map<String,Integer>> listData = new ArrayList<>();
        for (OrderSetting orderSetting : list) {
            int date = orderSetting.getOrderdate().getDate();
            Integer number = orderSetting.getNumber();
            Integer reservations = orderSetting.getReservations();

            Map<String,Integer> map = new HashMap<>();
            map.put("date",date);
            map.put("number",number);
            map.put("reservations",reservations);

            listData.add(map);
        }

        return listData;
    }

    /**
     * 设置预约人数
     * @param orderSetting
     */
    @Override
    public void edit(OrderSetting orderSetting) {

      Long count = orderSettingMapper.countBydate(orderSetting.getOrderdate());
      if (count > 0){
          orderSettingMapper.update(orderSetting);
      }else {
          orderSettingMapper.add(orderSetting);
          
      }



    }
}
