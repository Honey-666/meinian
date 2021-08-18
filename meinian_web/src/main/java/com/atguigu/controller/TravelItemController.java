package com.atguigu.controller;


import com.alibaba.dubbo.config.annotation.Reference;

import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.TravelItem;
import com.atguigu.service.TravelItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/travelItem")
public class TravelItemController {

    @Reference
    private TravelItemService travelItemService;

    /**
     * 添加自由行
     *
     * @param travelItem
     * @return
     */
    @RequestMapping("/add")
    @PreAuthorize("hasAnyAuthority('TRAVELITEM_ADD')")
    public Result add(@RequestBody TravelItem travelItem) {
        try {
            travelItemService.add(travelItem);

            return new Result(true, MessageConstant.ADD_TRAVELITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_TRAVELITEM_FAIL);
        }
    }

    /**
     * 分页
     *
     * @param queryPageBean
     * @return
     */

    @RequestMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {

        try {
            PageResult pageResult = travelItemService.findPage(queryPageBean);
            return new Result(true, MessageConstant.QUERY_TRAVELITEM_SUCCESS, pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_TRAVELITEM_FAIL);

        }
    }

    /**
     * 删除自由行
     *
     * @param id
     * @return
     */

    @RequestMapping("/delete")
    @PreAuthorize("hasAnyAuthority('TRAVELITEM_DELETE')")
    public Result delete(Integer id) {

        try {
            travelItemService.deleteTravelItem(id);

            return new Result(true, MessageConstant.DELETE_TRAVELITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, e.getMessage());

        }
    }

    /**
     * 通过id携带回去数据
     *
     * @param id
     * @return
     */

    @RequestMapping("/findById")
    public Result findById(Integer id) {

        try {
            TravelItem travelItem = travelItemService.findById(id);

            return new Result(true, MessageConstant.DELETE_TRAVELITEM_SUCCESS, travelItem);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, e.getMessage());

        }
    }

    /**
     * 修改自由行数据
     * @param travelItem
     * @return
     */

    @RequestMapping("/edit")
    @PreAuthorize("hasAnyAuthority('TRAVELITEM_EDIT')")
    public Result edit(@RequestBody TravelItem travelItem) {

        try {
            travelItemService.edit(travelItem);
            return new Result(true,MessageConstant.EDIT_TRAVELITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_TRAVELITEM_FAIL);
        }
    }

    /**
     * 获取所有的自由行数据发哦跟团游的表单中
     * @return
     */

    @RequestMapping("/findAll")
    public Result findAll() {

        try {
            List<TravelItem> list = travelItemService.findAll();
            return new Result(true, MessageConstant.QUERY_TRAVELITEM_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_TRAVELITEM_FAIL);

        }
    }

}
