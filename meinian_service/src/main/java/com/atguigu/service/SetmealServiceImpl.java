package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.SetmealMapper;
import com.atguigu.dao.SetmealTravelgroupMapper;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.Setmeal;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;

    @Autowired
    private SetmealTravelgroupMapper setmealTravelgroupMapper;


    /**
     * 添加套餐游
     * @param travelgroupIds
     * @param setmeal
     */
    @Override
    public void add(Integer[] travelgroupIds, Setmeal setmeal) {
        setmealMapper.add(setmeal);

        setmealTravelgroupMapper.insert(travelgroupIds,setmeal.getId());
    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {

        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
       List<Setmeal> list = setmealMapper.findPage(queryPageBean);
        PageInfo<Setmeal> pageInfo = new PageInfo<>(list);
        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 删除套餐游
     * @param id
     */
    @Override
    public void delete(Integer id) {

      Long count =  setmealTravelgroupMapper.count(id);

      if (count>0){
          throw new RuntimeException("有关联数据，不能删除");
      }

        setmealMapper.delete(id);
    }

    /**
     * 通过id携带套餐游数据
     * @param id
     * @return
     */
    @Override
    public Setmeal findById(Integer id) {


        return setmealMapper.findById(id);
    }

    /**
     * 获取跟团游id数组
     * @param id
     * @return
     */
    @Override
    public List<Integer> findSetmealIdByTravelgroupId(Integer id) {

        return  setmealTravelgroupMapper.findSetmealIdByTravelgroupId(id);
    }

    /**
     * 修改套餐游
     * @param travelgroupIds
     * @param setmeal
     */
    @Override
    public void edit(Integer[] travelgroupIds, Setmeal setmeal) {
        setmealMapper.edit(setmeal);

        setmealTravelgroupMapper.delete(setmeal.getId());

        setmealTravelgroupMapper.insert(travelgroupIds,setmeal.getId());
    }

    /**
     * 查询套餐游显示到app端
     * @return
     */
    @Override
    public List<Setmeal> getSetmealAll() {

        return setmealMapper.getSetmealAll();
    }

    /**
     * 查询套餐游到预约界面
     * @param id
     * @return
     */
    @Override
    public Setmeal findSetmealById(Integer id) {

        return  setmealMapper.findSetmealById(id);
    }

    /**
     * 套餐预约占比 圆饼图
     * @return
     */
    @Override
    public List<Map<String, Object>> findSetmenlCount() {
        List<Map<String, Object>> setmenlCount = setmealMapper.findSetmenlCount();
        return setmenlCount;
    }

}
