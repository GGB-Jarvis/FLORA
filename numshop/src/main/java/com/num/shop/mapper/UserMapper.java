package com.num.shop.mapper;

import java.util.List;

import com.num.shop.entity.Ranking;
import org.apache.ibatis.annotations.Mapper;
import com.num.shop.entity.User;

@Mapper
public interface UserMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<User> listAll(User user);
	User selectByCode(String code);
	Integer total(User user);

	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	User getById(Integer id);

	List<Ranking> findRanking();

	User getByOpenId(String openid);

	User getByPhone(String phone);

	/**
     * 新增，插入所有字段
     *
     * @param user 新增的记录
     * @return 返回影响行数
     */
	int insert(User user);
	
	/**
     * 新增，忽略null字段
     *
     * @param user 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(User user);
	
	/**
     * 修改，修改所有字段
     *
     * @param user 修改的记录
     * @return 返回影响行数
     */
	int update(User user);
	
	/**
     * 修改，忽略null字段
     *
     * @param user 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(User user);
	int updatePass(User user);

	/**
     * 删除记录
     *
     * @param user 待删除的记录
     * @return 返回影响行数
     */
	int delete(User user);
	
}