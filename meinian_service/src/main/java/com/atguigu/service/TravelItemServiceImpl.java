package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.TravelGroupTravelItemMapper;
import com.atguigu.dao.TravelItemMapper;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.TravelItem;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = TravelItemService.class)
@Transactional
public class TravelItemServiceImpl implements TravelItemService{

    @Autowired
    private TravelItemMapper travelItemMapper;

    @Autowired
    private TravelGroupTravelItemMapper travelGroupTravelItemMapper;

    /**
     * 添加自由行
     * @param travelItem
     */
    @Override
    public void add(TravelItem travelItem) {

       travelItemMapper.add(travelItem);
    }

    /**
     * 分页
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {

        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());

        List<TravelItem> travelItems =  travelItemMapper.selectTravelItem(queryPageBean);

        PageInfo<TravelItem> page = new PageInfo<>(travelItems);

        return new PageResult(page.getTotal(),page.getList());
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void deleteTravelItem(Integer id) {

        Long count = travelGroupTravelItemMapper.count(id);

        if (count > 0 ){
         throw new RuntimeException("有关联数据，不能删除");
        }

        travelItemMapper.deleteById(id);
    }


    /**
     * 通过id携带回去数据
     * @param id
     * @return
     */
    @Override
    public TravelItem findById(Integer id) {
       TravelItem travelItem = travelItemMapper.findById(id);
        return travelItem;
    }

    /**
     * 修改自由行
     * @param travelItem
     */
    @Override
    public void edit(TravelItem travelItem) {

        travelItemMapper.edit(travelItem);
    }

    /**
     * 获取所有的自由行数据发哦跟团游的表单中
     * @return
     */
    @Override
    public List<TravelItem> findAll() {

        return travelItemMapper.findAll();
    }

}
