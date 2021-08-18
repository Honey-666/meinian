package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.TravelGroup;
import com.atguigu.pojo.TravelItem;
import com.atguigu.service.TravelGroupService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/travelgroup")
public class TravelGroupController {

    @Reference
    private TravelGroupService travelGroupService;

    /**
     * 新增跟团游
     *
     * @param travelItemIds
     * @param travelGroup
     * @return
     */
    @RequestMapping("/add")
    public Result add(Integer[] travelItemIds, @RequestBody TravelGroup travelGroup) {

        try {
            travelGroupService.add(travelItemIds, travelGroup);

            return new Result(true, MessageConstant.ADD_TRAVELGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_TRAVELGROUP_FAIL);

        }
    }

    /**
     * 跟团游分页
     *
     * @return
     */
    @RequestMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {

        try {
            PageResult pageResult =  travelGroupService.findPage(queryPageBean);

            return new Result(true,MessageConstant.QUERY_TRAVELGROUP_SUCCESS,pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true,MessageConstant.QUERY_TRAVELGROUP_FAIL);

        }
    }

    /**
     * 删除跟团游
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {

        try {
            travelGroupService.delete(id);

            return new Result(true,MessageConstant.DELETE_TRAVELGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_TRAVELGROUP_FAIL);

        }
    }

    /**
     * 通过id携带编辑数据
     * @param id
     * @return
     */

    @RequestMapping("/findById")
    public Result findById(Integer id) {

        try {
            TravelGroup travelGroup =  travelGroupService.findById(id);

            return new Result(true,MessageConstant.QUERY_TRAVELGROUP_SUCCESS,travelGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_TRAVELGROUP_FAIL);

        }
    }

    /**
     * 查询已选择的自由行id数组
     * @param id
     * @return
     */

    @RequestMapping("/findTravelItemIdByTravelgroupId")
    public Result findTravelItemIdByTravelgroupId(Integer id) {

        try {
            List<Integer> list =  travelGroupService.findTravelItemIdByTravelgroupId(id);

            return new Result(true,MessageConstant.QUERY_TRAVELGROUP_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_TRAVELGROUP_FAIL);

        }
    }

    /**
     * 修改
     * @param travelItemIds
     * @param travelGroup
     * @return
     */

    @RequestMapping("/edit")
    public Result edit(Integer[] travelItemIds,@RequestBody TravelGroup travelGroup) {

        try {
            travelGroupService.edit(travelItemIds,travelGroup);

            return new Result(true,MessageConstant.EDIT_TRAVELGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_TRAVELGROUP_FAIL);

        }
    }

    /**
     * 查询所有的跟团游显示到套餐游的新增表单中
     * @return
     */
    @RequestMapping("/findAll")
    public Result findAll() {
        try {
          List<TravelGroup> list = travelGroupService.findAll();

            return new Result(true,MessageConstant.QUERY_TRAVELGROUP_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_TRAVELGROUP_FAIL);

        }
    }
}
