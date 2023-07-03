package com.num.shop.service;

import com.num.shop.entity.Orders;
import com.num.shop.mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    public List<Orders> listAll(Orders orders) {
    	return ordersMapper.listAll(orders);
    }

	public Integer total(Orders orders) {
        return ordersMapper.total(orders);
    }
    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    public Orders getById(Integer id) {
    	return ordersMapper.getById(id);
    }
	
    /**
     * 新增，插入所有字段
     *
     * @param orders 新增的记录
     * @return 返回影响行数
     */
    public int insert(Orders orders) {
    	return ordersMapper.insert(orders);
    }
	
    /**
     * 新增，忽略null字段
     *
     * @param orders 新增的记录
     * @return 返回影响行数
     */
    public int insertIgnoreNull(Orders orders) {
    	return ordersMapper.insertIgnoreNull(orders);
    }
	
    /**
     * 修改，修改所有字段
     *
     * @param orders 修改的记录
     * @return 返回影响行数
     */
    public int update(Orders orders) {
    	return ordersMapper.update(orders);
    }
	
    /**
     * 修改，忽略null字段
     *
     * @param orders 修改的记录
     * @return 返回影响行数
     */
    public int updateIgnoreNull(Orders orders) {
    	return ordersMapper.updateIgnoreNull(orders);
    }
	
    /**
     * 删除记录
     *
     * @param orders 待删除的记录
     * @return 返回影响行数
     */
    public int delete(Orders orders) {
    	return ordersMapper.delete(orders);
    }
	
}