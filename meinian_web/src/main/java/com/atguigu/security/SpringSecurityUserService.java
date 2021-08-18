package com.atguigu.security;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.pojo.User;
import com.atguigu.service.PermissionService;
import com.atguigu.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SpringSecurityUserService implements UserDetailsService {

    @Reference
    private UserService userService;

    @Reference
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = userService.getUserIndoByUsername(username);

      if (user == null){
          return null;
      }
        //查询权限信息
       List<String> authorities = permissionService.getAuthoritiesByUid(user.getId());

        List<SimpleGrantedAuthority> list = new ArrayList<>();

        for (String authority : authorities) {
            //授权
            list.add(new SimpleGrantedAuthority(authority));
        }

        /**
         * User()
         * 1：指定用户名
         * 2：指定密码（SpringSecurity会自动对密码进行校验）
         * 3：传递授予的角色和权限
         */
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),list);
    }
}
