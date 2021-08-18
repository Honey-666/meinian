package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.constant.MessageConstant;
import com.atguigu.dao.MemberMapper;
import com.atguigu.dao.OrderMapper;
import com.atguigu.dao.OrderSettingMapper;
import com.atguigu.entity.Result;
import com.atguigu.pojo.*;
import com.atguigu.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderSettingMapper orderSettingMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private MemberMapper memberMapper;

    /**
     * 预约功能的实现
     * <p>
     * 需要验证的几个问题：
     * 1、检查用户所选择的预约日期是否已经提前进行了预约设置，如果没有设置则无法进行预约
     * <p>
     * 2、检查用户所选择的预约日期是否已经约满，如果已经约满则无法预约
     * <p>
     * 3、检查用户是否重复预约（同一个用户在同一天预约了同一个套餐），如果是重复预约则无法完成再次预约
     * <p>
     * 4、检查当前用户是否为会员，如果是会员则直接完成预约，如果不是会员则自动完成注册并进行预约
     * <p>
     * 5、预约成功，更新当日的已预约人数
     *
     * @param map
     * @return
     */
    @Override
    public Result submit(Map<String, String> map) throws Exception {
        //1、检查用户所选择的预约日期是否已经提前进行了预约设置，如果没有设置则无法进行预约

        //获取预约的时间
        String orderDate = map.get("orderDate");
        //获取手机号
        String telephone = map.get("telephone");
        //创建预约order对象
        Order order = null;
        //创建Member
        Member member = new Member();
        //获取套餐的id
        String setmealId = map.get("setmealId");
       /* //将时间转换为事件类型
        Date date = DateUtils.parseString2Date(orderDate);*/
        //查询是否开团
        OrderSetting orderSetting = orderSettingMapper.selectOrderSettingId(orderDate);

        if (orderSetting == null) {
            throw new RuntimeException(MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        } else {
            // 2、检查用户所选择的预约日期是否已经约满，如果已经约满则无法预约

            //获取预约人数和可预约人数
            //比较已预约人数和总共有过少个预约数量
            Integer number = orderSetting.getNumber();
            Integer reservations = orderSetting.getReservations();

            if (reservations >= number) {
                throw new RuntimeException(MessageConstant.ORDER_FULL);
            }

            //如果会员库中没有这个信息，先对他进行会员的注册

            //通过手机号查询出来的会员
            MemberExample example = new MemberExample();
            MemberExample.Criteria criteria = example.createCriteria();
            criteria.andPhonenumberEqualTo(telephone);
            List<Member> members = memberMapper.selectByExample(example);


            if (members == null || members.size() <= 0) {
                //没有会员进行注册
                String name = map.get("name");
                String sex = map.get("sex");
                String idCard = map.get("idCard");
                //给会员赋值
                member.setName(name);
                member.setSex(sex);
                member.setIdcard(idCard);
                member.setPhonenumber(telephone);
                member.setRegtime(new Date());

                //注册会员
                memberMapper.insert(member);
            } else {

                //3、检查用户是否重复预约（同一个用户在同一天预约了同一个套餐），如果是重复预约则无法完成再次预约

                //判断是否已经预约过一次了   通过会员id 时间  和套餐id
                OrderExample example1 = new OrderExample();
                OrderExample.Criteria criteria1 = example1.createCriteria();
                //通过会员的id进行对比
                member = members.get(0);
                criteria1.andIdEqualTo(member.getId());
                //通过时间对比
                criteria1.andOrderdateEqualTo(DateUtils.parseString2Date(orderDate));
                //判断套餐id是否相同
                criteria1.andSetmealIdEqualTo(Integer.parseInt(setmealId));
                long count = orderMapper.countByExample(example1);

                if (count > 0) {
                    return new Result(false, MessageConstant.HAS_ORDERED);
                }
            }

            //可以预约了
            String type = "微信预约";
            String status = "未出游";
            order = new Order(member.getId(), DateUtils.parseString2Date(orderDate),
                    type, status, Integer.parseInt(setmealId));

            orderMapper.insert(order);
            //让预约人数加一
            orderSettingMapper.updateReservations(orderDate, reservations + 1);
            return new Result(true, MessageConstant.ORDER_SUCCESS, order.getId());
        }

    }

    @Override
    public Map<String, Object> findById(Integer id) {

        Map<String, Object> map = orderMapper.findById(id);

        return map;
    }

}
