package com.num.shop.service;

import com.num.shop.entity.Slideshow;
import com.num.shop.mapper.SlideshowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlideshowService {

    @Autowired
    private SlideshowMapper slideshowMapper;

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    public List<Slideshow> listAll(Slideshow slideshow) {
    	return slideshowMapper.listAll(slideshow);
    }

	public Integer total(Slideshow slideshow) {
        return slideshowMapper.total(slideshow);
    }
    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    public Slideshow getById(Integer id) {
    	return slideshowMapper.getById(id);
    }
	
    /**
     * 新增，插入所有字段
     *
     * @param slideshow 新增的记录
     * @return 返回影响行数
     */
    public int insert(Slideshow slideshow) {
    	return slideshowMapper.insert(slideshow);
    }
	
    /**
     * 新增，忽略null字段
     *
     * @param slideshow 新增的记录
     * @return 返回影响行数
     */
    public int insertIgnoreNull(Slideshow slideshow) {
    	return slideshowMapper.insertIgnoreNull(slideshow);
    }
	
    /**
     * 修改，修改所有字段
     *
     * @param slideshow 修改的记录
     * @return 返回影响行数
     */
    public int update(Slideshow slideshow) {
    	return slideshowMapper.update(slideshow);
    }
	
    /**
     * 修改，忽略null字段
     *
     * @param slideshow 修改的记录
     * @return 返回影响行数
     */
    public int updateIgnoreNull(Slideshow slideshow) {
    	return slideshowMapper.updateIgnoreNull(slideshow);
    }
	
    /**
     * 删除记录
     *
     * @param slideshow 待删除的记录
     * @return 返回影响行数
     */
    public int delete(Slideshow slideshow) {
    	return slideshowMapper.delete(slideshow);
    }
	
}