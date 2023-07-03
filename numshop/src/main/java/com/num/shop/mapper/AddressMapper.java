package com.num.shop.mapper;

import com.num.shop.entity.Address;
import com.num.shop.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther: lc
 * @Date: 2023/4/28 18:16
 * @Description:
 * 地址接口
 */
@Mapper
public interface AddressMapper {
    /**
     * 查所有+条件查+分页查
     * @param address
     * @return list
     */
    List<Address> list(Address address);

    /**
     * 总数量+满足某条件的数量
     */
    Integer total(Address address);

    /**
     * 通过id获取某一地址信息
     */
    Address getById(Integer id);

    /**
     * 插入地址信息
     */
    Integer insert(Address address);

    /**
     * 修改地址信息
     */
    Integer update(Address address);


    /**
     * 删除记录
     */
    Integer delete(Address address);

}
