package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.pojo.Order;
import com.atguigu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@RequestMapping("/order")
@RestController
public class OrderController {


    @Autowired
    private JedisPool jedisPool;

    @Reference
    private OrderService orderService;


    /**
     * 预约功能的实现
     *
     * @param map
     * @return
     */
    @RequestMapping("/submit")
    public Result submit(@RequestBody Map<String, String> map) {

        //获取验证码验证看看是否验证通过
        String validateCode = map.get("validateCode");
        //通过电话号码从redis中获取验证码进行比对
        String telephone = map.get("telephone");
        String redisCode = jedisPool.getResource().get(telephone);
        //如果不匹配就返回一个错误信息
        if (redisCode != null && !"".equals(redisCode) && redisCode.equals(validateCode)) {
            try {
                //预约成功
                Result result = orderService.submit(map);
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return new Result(false, e.getMessage());

            }

        }
        return new Result(false, MessageConstant.VALIDATECODE_ERROR);
    }

    /**
     * 预约成功界面数据回显
     * @param
     * @return
     */
    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            Map<String, Object> map = orderService.findById(id);
            return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,map);
        } catch (Exception e){
            return new Result(true,MessageConstant.QUERY_ORDER_FAIL);
        }
    }
}
