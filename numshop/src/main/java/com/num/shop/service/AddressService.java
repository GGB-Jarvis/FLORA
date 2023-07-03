package com.num.shop.service;

import com.num.shop.entity.Address;
import com.num.shop.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: lc
 * @Date: 2023/4/28 18:30
 * @Description:
 */
@Component
public class AddressService {
    @Autowired
    private AddressMapper addressMapper;


    /**
     * 查所有+条件查+分页查
     * @param address
     * @return list
     */
    public List<Address> list(Address address){
        return addressMapper.list(address);
    }

    /**
     * 总数量+满足某条件的数量
     */
    public Integer total(Address address){
        return addressMapper.total(address);
    }

    /**
     * 通过id获取某一地址信息
     */
    public Address getById(Integer id){
        return addressMapper.getById(id);
    }

    /**
     * 插入地址信息
     */
    public Integer insert(Address address){
        return addressMapper.insert(address);
    }

    /**
     * 修改地址信息
     */
    public Integer update(Address address){
        return addressMapper.update(address);
    }


    /**
     * 删除记录
     */
    public Integer delete(Address address){
        return addressMapper.delete(address);
    }
}
