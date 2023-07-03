package com.num.shop.service;

import com.num.shop.entity.Details;
import com.num.shop.mapper.DetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailsService {

    @Autowired
    private DetailsMapper detailsMapper;

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    public List<Details> listAll(Details details) {
    	return detailsMapper.listAll(details);
    }

    public List<Details> listAll2(Integer userid) {
        return detailsMapper.listAll2(userid);
    }

	public Integer total(Details details) {
        return detailsMapper.total(details);
    }
    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    public Details getById(Integer id) {
    	return detailsMapper.getById(id);
    }
	
    /**
     * 新增，插入所有字段
     *
     * @param details 新增的记录
     * @return 返回影响行数
     */
    public int insert(Details details) {
    	return detailsMapper.insert(details);
    }
	
    /**
     * 新增，忽略null字段
     *
     * @param details 新增的记录
     * @return 返回影响行数
     */
    public int insertIgnoreNull(Details details) {
    	return detailsMapper.insertIgnoreNull(details);
    }
	
    /**
     * 修改，修改所有字段
     *
     * @param details 修改的记录
     * @return 返回影响行数
     */
    public int update(Details details) {
    	return detailsMapper.update(details);
    }
	
    /**
     * 修改，忽略null字段
     *
     * @param details 修改的记录
     * @return 返回影响行数
     */
    public int updateIgnoreNull(Details details) {
    	return detailsMapper.updateIgnoreNull(details);
    }
	
    /**
     * 删除记录
     *
     * @param details 待删除的记录
     * @return 返回影响行数
     */
    public int delete(Details details) {
    	return detailsMapper.delete(details);
    }
	
}