package com.atguigu.service;

import java.util.List;

public interface PermissionService {
    List<String> getAuthoritiesByUid(Integer id);
}
