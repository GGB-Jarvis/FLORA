package com.num.shop.service;

import com.num.shop.entity.Notes;
import com.num.shop.mapper.NotesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {

    @Autowired
    private NotesMapper notesMapper;

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    public List<Notes> listAll(Notes notes) {
    	return notesMapper.listAll(notes);
    }

	public Integer total(Notes notes) {
        return notesMapper.total(notes);
    }
    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    public Notes getById(Integer id) {
    	return notesMapper.getById(id);
    }
	
    /**
     * 新增，插入所有字段
     *
     * @param notes 新增的记录
     * @return 返回影响行数
     */
    public int insert(Notes notes) {
    	return notesMapper.insert(notes);
    }
	
    /**
     * 新增，忽略null字段
     *
     * @param notes 新增的记录
     * @return 返回影响行数
     */
    public int insertIgnoreNull(Notes notes) {
    	return notesMapper.insertIgnoreNull(notes);
    }
	
    /**
     * 修改，修改所有字段
     *
     * @param notes 修改的记录
     * @return 返回影响行数
     */
    public int update(Notes notes) {
    	return notesMapper.update(notes);
    }
	
    /**
     * 修改，忽略null字段
     *
     * @param notes 修改的记录
     * @return 返回影响行数
     */
    public int updateIgnoreNull(Notes notes) {
    	return notesMapper.updateIgnoreNull(notes);
    }
	
    /**
     * 删除记录
     *
     * @param notes 待删除的记录
     * @return 返回影响行数
     */
    public int delete(Notes notes) {
    	return notesMapper.delete(notes);
    }
	
}