package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.MemberMapper;
import com.atguigu.entity.Result;
import com.atguigu.pojo.MemberExample;
import com.atguigu.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(interfaceClass = MemberService.class)
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberMapper memberMapper;


    /**
     * 会员注册曲线去显示
     * @param months
     * @return
     * @throws Exception
     */
    @Override
    public List<Integer> getMemberCountByDate(List<String> months) throws Exception {
        List<Integer> list = new ArrayList<>();

        //遍历出每个月
        for (String month : months) {
           //获取每个月的最后一天
            String lastDayOfMonth = DateUtils.getLastDayOfMonth(month);
            //获取每个月的会员数量
            MemberExample example = new MemberExample();
            MemberExample.Criteria criteria = example.createCriteria();
            criteria.andRegtimeLessThanOrEqualTo(DateUtils.parseString2Date(lastDayOfMonth));
            Long count = memberMapper.countByExample(example);
            list.add(count.intValue());
        }
        return list;
    }



}
