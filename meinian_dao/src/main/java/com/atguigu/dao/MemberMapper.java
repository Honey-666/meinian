package com.atguigu.dao;

import com.atguigu.pojo.Member;
import com.atguigu.pojo.MemberExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberMapper {
    long countByExample(MemberExample example);

    int deleteByExample(MemberExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Member record);

    int insertSelective(Member record);

    List<Member> selectByExample(MemberExample example);

    Member selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Member record, @Param("example") MemberExample example);

    int updateByExample(@Param("record") Member record, @Param("example") MemberExample example);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);

    int getNewMemberCountBYToday(String today);

    int getAllMeberCount();

    int getThisWeekNewMember(String thisWeekMonday);

    int getThisMonthNewMember(String firstDayThisMonth);
}