package com.atguigu.controller;


import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.utils.SMSUtils;
import com.atguigu.utils.SMSUtilsNew;
import com.atguigu.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/send4Order")
    public Result send4Order(String telephone){
        try {
            //通过工具类获取随机验证码
            Integer code = ValidateCodeUtils.generateValidateCode(6);
            //通过阿里云工具类发送验证码(邓嘉琪版)
            SMSUtilsNew.sendShortMessage(telephone,code.toString());
            //老师版本
//            SMSUtils.sendShortMessage(telephone,code.toString());

            //将手机号作为key，验证码作为value保存的redis中做缓存，让验证码在5分钟内有效
            Jedis jedis = jedisPool.getResource();
            jedis.setex(telephone,60*5,code.toString());
//            jedis.set(telephone,code.toString());
            return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);

        }
    }

}
