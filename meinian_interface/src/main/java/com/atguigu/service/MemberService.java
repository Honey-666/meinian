package com.atguigu.service;

import java.util.List;

public interface MemberService {
    List<Integer> getMemberCountByDate(List<String> months) throws Exception;
}
