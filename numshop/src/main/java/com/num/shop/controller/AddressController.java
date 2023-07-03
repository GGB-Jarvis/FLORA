package com.num.shop.controller;

import com.num.shop.entity.Address;
import com.num.shop.entity.ShoppingCar;
import com.num.shop.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: 123
 * @Date: 2023/4/28 18:33
 * @Description:
 * 地区处理器
 */
@RestController
@RequestMapping("/address")
public class AddressController {
    /**
     * 引入依赖
     */
    @Autowired
    private AddressService addressService;

    /**
     * 查询所有+分页+条件查询
     */
    @RequestMapping("list")
    public List<Address> list(Address address){
        //分页
        if(address.getPageIndex()!=null)
            address.setStart((address.getPageIndex()-1)*address.getPageSize());

        return addressService.list(address);
    }

    /**
     * 查询记录条数
     */
    @RequestMapping("total")
    public Integer total(Address address) {
        return addressService.total(address);
    }

    /**
     * 根据id查询
     */
    @RequestMapping("getById")
    public Address getById(Integer id) {
        return addressService.getById(id);
    }

    /**
     * 新增
     */
    @RequestMapping("insert")
    public int add(Address address) {
        return addressService.insert(address);
    }

    /**
     * 修改
     */
    @RequestMapping("update")
    public int update(Address address){
        return addressService.update(address);
    }

    /**
     * 删除
     */
    @RequestMapping("delete")
    public Integer delete(Address address){
        return  addressService.delete(address);
    }

}
