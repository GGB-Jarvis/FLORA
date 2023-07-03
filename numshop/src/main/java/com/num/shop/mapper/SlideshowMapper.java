package com.num.shop.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.num.shop.entity.Slideshow;

@Mapper
public interface SlideshowMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<Slideshow> listAll(Slideshow slideshow);

	Integer total(Slideshow slideshow);

	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	Slideshow getById(Integer id);
	
	/**
     * 新增，插入所有字段
     *
     * @param slideshow 新增的记录
     * @return 返回影响行数
     */
	int insert(Slideshow slideshow);
	
	/**
     * 新增，忽略null字段
     *
     * @param slideshow 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(Slideshow slideshow);
	
	/**
     * 修改，修改所有字段
     *
     * @param slideshow 修改的记录
     * @return 返回影响行数
     */
	int update(Slideshow slideshow);
	
	/**
     * 修改，忽略null字段
     *
     * @param slideshow 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(Slideshow slideshow);
	
	/**
     * 删除记录
     *
     * @param slideshow 待删除的记录
     * @return 返回影响行数
     */
	int delete(Slideshow slideshow);
	
}