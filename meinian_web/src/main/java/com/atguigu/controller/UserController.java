package com.atguigu.controller;

import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 获取登陆的用户名
      * @return
     */
    @RequestMapping("/getUsername")
    public Result getUsername(){
        try {
            //能够获取到SpringSecurityUserService 里面user对象  固定写法
            //是import org.springframework.security.core.userdetails.User; 这个包下的user
           User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return new Result(true, MessageConstant.GET_USERNAME_SUCCESS,principal);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_USERNAME_FAIL);

        }
    }
}
