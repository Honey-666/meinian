package com.atguigu.dao;


import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.TravelItem;

import java.util.List;

public interface TravelItemMapper {

    public void add(TravelItem travelItem);


    List<TravelItem> selectTravelItem(QueryPageBean queryString);

    void deleteById(Integer id);

    TravelItem findById(Integer id);

    void edit(TravelItem travelItem);

    List<TravelItem> findAll();

    List<TravelItem> findTravelItemByTravelId(Integer id);
}
