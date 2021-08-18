package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.constant.RedisConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.Setmeal;
import com.atguigu.service.SetmealService;
import com.atguigu.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Reference
    private SetmealService setmealService;

    @Autowired
    private JedisPool jedisPool;

    /**
     * 图片上传
     *
     * @param imgFile
     * @return
     */

    @RequestMapping("/upload")
    public Result upload(MultipartFile imgFile) {
        Jedis jedis = null;
        try {
            //使用uuid加上原来图片的名字
            String filename = UUID.randomUUID().toString().replace("-", "") + imgFile.getOriginalFilename();

            QiniuUtils.upload2Qiniu(imgFile.getBytes(), filename);
            jedis = jedisPool.getResource();
            jedis.sadd(RedisConstant.SETMEAL_PIC_RESOURCES,filename);

            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, filename);

        } catch (IOException e) {
            e.printStackTrace();

            return new Result(true, MessageConstant.PIC_UPLOAD_FAIL);

        }finally {
            jedis.close();
        }

    }

    /**
     * 添加套餐游
     *
     * @param travelgroupIds
     * @param setmeal
     * @return
     */

    @RequestMapping("/add")
    public Result add(Integer[] travelgroupIds, @RequestBody Setmeal setmeal) {
        Jedis jedis = null;
        try {
            setmealService.add(travelgroupIds, setmeal);
            jedis = jedisPool.getResource();
            jedis.sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,setmeal.getImg());

            return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, MessageConstant.ADD_SETMEAL_FAIL);

        }finally {
            jedis.close();
        }
    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {

        try {
            PageResult pageResult = setmealService.findPage(queryPageBean);

            return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_SETMEAL_FAIL);

        }
    }

    /**
     * 删除套餐游
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {

        try {
            setmealService.delete(id);
            return new Result(true,MessageConstant.DELETE_SETMEAL_LIST_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,e.getMessage());

        }
    }

    /**
     * 通过id携带套餐游数据
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public Result findById(Integer id) {

        try {
           Setmeal setmeal = setmealService.findById(id);
            return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_SETMEAL_FAIL);

        }
    }

    /**
     * 获取跟团游id数组
     * @param id
     * @return
     */
    @RequestMapping("/findSetmealIdByTravelgroupId")
    public Result findSetmealIdByTravelgroupId(Integer id) {

        try {
            List<Integer> list = setmealService.findSetmealIdByTravelgroupId(id);
            return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_SETMEAL_FAIL);

        }
    }


    /**
     * 修改套餐游
     * @param travelgroupIds
     * @param setmeal
     * @return
     */
    @RequestMapping("/edit")
    public Result edit(Integer[] travelgroupIds,@RequestBody Setmeal setmeal) {

        try {
            setmealService.edit(travelgroupIds,setmeal);
            return new Result(true,MessageConstant.EDIT_SETMEAL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_SETMEAL_FAIL);

        }
    }

}
