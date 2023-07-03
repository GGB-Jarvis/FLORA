package com.num.shop.service;

import com.num.shop.entity.Product;
import com.num.shop.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    public List<Product> listAll(Product product) {
    	return productMapper.listAll(product);
    }

	public Integer total(Product product) {
        return productMapper.total(product);
    }
    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    public Product getById(Integer id) {
    	return productMapper.getById(id);
    }
	
    /**
     * 新增，插入所有字段
     *
     * @param product 新增的记录
     * @return 返回影响行数
     */
    public int insert(Product product) {
    	return productMapper.insert(product);
    }
	
    /**
     * 新增，忽略null字段
     *
     * @param product 新增的记录
     * @return 返回影响行数
     */
    public int insertIgnoreNull(Product product) {
    	return productMapper.insertIgnoreNull(product);
    }
	
    /**
     * 修改，修改所有字段
     *
     * @param product 修改的记录
     * @return 返回影响行数
     */
    public int update(Product product) {
    	return productMapper.update(product);
    }
	
    /**
     * 修改，忽略null字段
     *
     * @param product 修改的记录
     * @return 返回影响行数
     */
    public int updateIgnoreNull(Product product) {
    	return productMapper.updateIgnoreNull(product);
    }
	
    /**
     * 删除记录
     *
     * @param product 待删除的记录
     * @return 返回影响行数
     */
    public int delete(Product product) {
    	return productMapper.delete(product);
    }
	
}