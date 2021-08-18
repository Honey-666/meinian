package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;



@Service(interfaceClass = RoleService.class)
@Transactional
public class RoleServiceImpl implements RoleService {

}
