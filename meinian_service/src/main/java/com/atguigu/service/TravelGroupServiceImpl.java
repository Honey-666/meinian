package com.atguigu.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.TravelGroupMapper;
import com.atguigu.dao.TravelGroupTravelItemMapper;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.TravelGroup;
import com.atguigu.pojo.TravelItem;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = TravelGroupService.class)
@Transactional
public class TravelGroupServiceImpl implements TravelGroupService{

    @Autowired
    private TravelGroupMapper travelGroupMapper;

    @Autowired
    private TravelGroupTravelItemMapper travelGroupTravelItemMapper;

    /**
     * 新增跟团游
     * @param travelItemIds
     * @param travelGroup
     */
    @Override
    public void add(Integer[] travelItemIds, TravelGroup travelGroup) {

        //先添加跟团游
        travelGroupMapper.insert(travelGroup);
        //返回跟团游的主键
        Integer travelGroupId = travelGroup.getId();

        //将跟团游的主键和自由行的id数组批量插入
        travelGroupTravelItemMapper.batchInsert(travelItemIds,travelGroupId);
    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {

       PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
       List<TravelGroup> list = travelGroupMapper.findPage(queryPageBean);

        PageInfo<TravelGroup> page = new PageInfo<>(list);

        return new PageResult(page.getTotal(),page.getList());
    }

    /**
     * 删除跟团游
     * @param id
     */
    @Override
    public void delete(Integer id) {
        travelGroupMapper.delete(id);
    }

    /**
     * 通过id携带编辑数据
     * @param id
     * @return
     */
    @Override
    public TravelGroup findById(Integer id) {
        return travelGroupMapper.findById(id);
    }

    /**
     * 查询已选择的自由行id数组
     * @param id
     * @return
     */
    @Override
    public List<Integer> findTravelItemIdByTravelgroupId(Integer id) {



        return  travelGroupTravelItemMapper.findTravelItemIdByTravelgroupId(id);
    }

    /**
     * 修改
     * @param travelItemIds
     * @param travelGroup
     */
    @Override
    public void edit(Integer[] travelItemIds, TravelGroup travelGroup) {
        travelGroupMapper.edit(travelGroup);

        travelGroupTravelItemMapper.delete(travelGroup.getId());

        travelGroupTravelItemMapper.batchInsert(travelItemIds,travelGroup.getId());
    }

    /**
     * 查询所有的跟团游显示到套餐游的新增表单中
     * @return
     */
    @Override
    public List<TravelGroup> findAll() {

        return travelGroupMapper.findAll();
    }
}
