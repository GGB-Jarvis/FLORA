package com.num.shop.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.num.shop.entity.Notes;

@Mapper
public interface NotesMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<Notes> listAll(Notes notes);

	Integer total(Notes notes);

	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	Notes getById(Integer id);
	
	/**
     * 新增，插入所有字段
     *
     * @param notes 新增的记录
     * @return 返回影响行数
     */
	int insert(Notes notes);
	
	/**
     * 新增，忽略null字段
     *
     * @param notes 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(Notes notes);
	
	/**
     * 修改，修改所有字段
     *
     * @param notes 修改的记录
     * @return 返回影响行数
     */
	int update(Notes notes);
	
	/**
     * 修改，忽略null字段
     *
     * @param notes 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(Notes notes);
	
	/**
     * 删除记录
     *
     * @param notes 待删除的记录
     * @return 返回影响行数
     */
	int delete(Notes notes);
	
}