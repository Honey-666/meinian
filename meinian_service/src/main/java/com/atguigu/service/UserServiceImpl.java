package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.UserMapper;
import com.atguigu.pojo.User;
import com.atguigu.pojo.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    /**
     * 查询用户
     * @param username
     * @return
     */
    @Override
    public User getUserIndoByUsername(String username) {

        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(example);

        if (users.size() >0){
            return users.get(0);
        }
        return null;
    }
}
