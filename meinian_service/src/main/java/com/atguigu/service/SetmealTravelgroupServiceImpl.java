package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass = SetmealTravelgroupService.class)
@Transactional
public class SetmealTravelgroupServiceImpl implements SetmealTravelgroupService{
//    @Autowired
}
