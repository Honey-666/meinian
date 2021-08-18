package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.AddressMapper;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.Address;
import com.atguigu.pojo.AddressExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = AddressService.class)
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;


    /**
     * 查询分校信息显示在地图上
     * @return
     */
    @Override
    public List<Address> findAll() {
        return addressMapper.selectByExample(null);
    }

    /**
     * 添加分校地址
     * @param address
     */
    @Override
    public void add(Address address) {
        addressMapper.insert(address);
    }

    /**
     * 分校地址分页查询
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {

        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        AddressExample example = new AddressExample();
        if (queryPageBean.getQueryString() != null && queryPageBean.getQueryString() != ""){
            AddressExample.Criteria criteria = example.createCriteria();
            criteria.andAddressnameLike("%"+queryPageBean.getQueryString()+"%");
        }
        List<Address> list = addressMapper.selectByExample(example);
        PageInfo<Address> page = new PageInfo<>(list);
        return new PageResult(page.getTotal(),page.getList());
    }

    /**
     *删除分校地址
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        addressMapper.deleteByPrimaryKey(id);
    }

}
