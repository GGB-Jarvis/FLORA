package com.num.shop.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.num.shop.entity.Product;

@Mapper
public interface ProductMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<Product> listAll(Product product);

	Integer total(Product product);

	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	Product getById(Integer id);
	
	/**
     * 新增，插入所有字段
     *
     * @param product 新增的记录
     * @return 返回影响行数
     */
	int insert(Product product);
	
	/**
     * 新增，忽略null字段
     *
     * @param product 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(Product product);
	
	/**
     * 修改，修改所有字段
     *
     * @param product 修改的记录
     * @return 返回影响行数
     */
	int update(Product product);
	
	/**
     * 修改，忽略null字段
     *
     * @param product 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(Product product);
	
	/**
     * 删除记录
     *
     * @param product 待删除的记录
     * @return 返回影响行数
     */
	int delete(Product product);
	
}