package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.Address;
import com.atguigu.service.AddressService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Reference
    private AddressService addressService;

    /**
     * 查询分校信息显示在地图上
     */
    @RequestMapping("/findAllMaps")
    public Map<String, Object> findAllMaps(){
      List<Address> list = addressService.findAll();
      //这个存储经纬度
      List<Map<String, String>> mapList = new ArrayList<>();
      //这个存储地址
      List<Map<String, String>> addressMap = new ArrayList<>();

        for (Address address : list) {
            String lng = address.getLng();
            String lat = address.getLat();
            String addressname = address.getAddressname();
            Map<String, String> map1 = new HashMap<>();
            map1.put("lng",lng);
            map1.put("lat",lat);
            mapList.add(map1);
            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("addressName",addressname);
            addressMap.add(hashMap);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("gridnMaps",mapList);
        map.put("nameMaps",addressMap);

        return map;
    }

    /**
     * 添加分校地址
     * @param address
     * @return
     */
    @RequestMapping("/addAddress")
    public Result addAddress(@RequestBody Address address){
        try {
            addressService.add(address);
            return new Result(true, MessageConstant.ADD_ADDRESS_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_ADDRESS_FAIL);

        }
    }

    /**
     * 分校地址分页查询
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        try {
          PageResult pageResult = addressService.findPage(queryPageBean);
            return new Result(true, MessageConstant.QUERY_ADDRESS_SUCCESS,pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_ADDRESS_FAIL);

        }
    }

    /**
     * 删除分校地址
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public Result deleteById(Integer id){
        try {
            addressService.deleteById(id);
            return new Result(true, MessageConstant.DELETE_ADDRESS_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_ADDRESS_FAIL);

        }
    }
}
