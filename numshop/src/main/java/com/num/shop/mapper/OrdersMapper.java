package com.num.shop.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.num.shop.entity.Orders;

@Mapper
public interface OrdersMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<Orders> listAll(Orders orders);

	Integer total(Orders orders);

	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	Orders getById(Integer id);
	
	/**
     * 新增，插入所有字段
     *
     * @param orders 新增的记录
     * @return 返回影响行数
     */
	int insert(Orders orders);
	
	/**
     * 新增，忽略null字段
     *
     * @param orders 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(Orders orders);
	
	/**
     * 修改，修改所有字段
     *
     * @param orders 修改的记录
     * @return 返回影响行数
     */
	int update(Orders orders);
	
	/**
     * 修改，忽略null字段
     *
     * @param orders 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(Orders orders);
	
	/**
     * 删除记录
     *
     * @param orders 待删除的记录
     * @return 返回影响行数
     */
	int delete(Orders orders);
	
}