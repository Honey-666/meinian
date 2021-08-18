package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.MemberMapper;
import com.atguigu.dao.OrderMapper;
import com.atguigu.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = ReportService.class)
@Transactional
public class ReportServiceImpl implements ReportService{

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 运营统计数据
     * @return
     */
    @Override
    public Map<String, Object> getBusinessReportData() throws Exception {
        /**
         *    reportDate:null, // 日期
         *                 todayNewMember :0, // 新增会员数
         *                 totalMember :0,// 总会员数
         *                 thisWeekNewMember :0,// 本周新增会员数
         *                 thisMonthNewMember :0,// 本月新增会员数
         *                 todayOrderNumber :0,// 今日预约数
         *                 todayVisitsNumber :0,// 今日出游数
         *                 thisWeekOrderNumber :0,// 本周预约数
         *                 thisWeekVisitsNumber :0,// 本周出游数
         *                 thisMonthOrderNumber :0,// 本月预约数
         *                 thisMonthVisitsNumber :0,// 本月出游数
         */

        Map<String, Object> map = new HashMap<>();
        //获取今天日期,然后进行格式转换
        String today = DateUtils.parseDate2String(DateUtils.getToday());
        map.put("reportDate",today);
        // 今天新增会员数
       int count = memberMapper.getNewMemberCountBYToday(today);
       map.put("todayNewMember",count);
        //总会员数
        int count1 = memberMapper.getAllMeberCount();
        map.put("totalMember",count1);
        //本周新增会员数
        String thisWeekMonday = DateUtils.parseDate2String(DateUtils.getThisWeekMonday());
        int count2 = memberMapper.getThisWeekNewMember(thisWeekMonday);
         map.put("thisWeekNewMember",count2);
        //本月新增会员数
        String firstDayThisMonth = DateUtils.parseDate2String(DateUtils.getFirstDay4ThisMonth());
       int count3 = memberMapper.getThisMonthNewMember(firstDayThisMonth);
       map.put("thisMonthNewMember",count3);
        //今日预约数
        int count4 = orderMapper.getTodayOrderNumber(today);
        map.put("todayOrderNumber",count4);
        //今日出游数
        int count5 = orderMapper.getTodayVisitsNumber(today);
        map.put("todayVisitsNumber",count5);
        //本周预约数
        String sunday = DateUtils.parseDate2String(DateUtils.getSundayOfThisWeek());
        int count6 = orderMapper.getThisWeekOrderNumber(thisWeekMonday,sunday);
        map.put("thisWeekOrderNumber",count6);
        //本周出游数
        int count7 = orderMapper.getThisWeekVisitsNumber(thisWeekMonday,sunday);
        map.put("thisWeekVisitsNumber",count7);
        //本月预约数
        String monthLast = DateUtils.parseDate2String(DateUtils.getLastDay4ThisMonth());
        int count8 = orderMapper.getThisMonthOrderNumber(firstDayThisMonth,monthLast);
        map.put("thisMonthOrderNumber",count8);
        //本月出游数
        int count9 = orderMapper.getThisMonthVisitsNumber(firstDayThisMonth,monthLast);
        map.put("thisMonthVisitsNumber",count9);
        //热门套餐
        List<Map<String, Object>> hotSetmeal = orderMapper.findHotSetmeal();
        map.put("hotSetmeal",hotSetmeal);
        return map;
    }
}
