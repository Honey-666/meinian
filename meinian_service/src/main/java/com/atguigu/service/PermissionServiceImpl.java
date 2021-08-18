package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = PermissionService.class)
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 通过用户id查询这个用户的权限信息
     * @param id
     * @return
     */
    @Override
    public List<String> getAuthoritiesByUid(Integer id) {

        List<String> authorities =  permissionMapper.getAuthoritiesByUid(id);

        return authorities;
    }
}
