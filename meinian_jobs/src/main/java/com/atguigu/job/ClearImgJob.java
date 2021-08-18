package com.atguigu.job;

import com.atguigu.constant.RedisConstant;
import com.atguigu.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

public class ClearImgJob {

    @Autowired
    private JedisPool jedisPool;

    //定时清理垃圾图片
    public void clearImg(){

        Jedis jedis = jedisPool.getResource();
        Set<String> sdiff = jedis.sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);

        for (String s : sdiff) {
            //1清理七牛云垃圾图片
            QiniuUtils.deleteFileFromQiniu(s);
            //2清理redis缓存中的垃圾图片
            jedis.srem(RedisConstant.SETMEAL_PIC_RESOURCES,s);
        }
    }
}
