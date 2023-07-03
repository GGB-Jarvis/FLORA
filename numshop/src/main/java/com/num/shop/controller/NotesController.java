package com.num.shop.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.num.shop.entity.Notes;
import com.num.shop.service.NotesService;

@RestController
@RequestMapping("/notes")
public class NotesController {

    @Autowired
    private NotesService notesService;

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    @RequestMapping("list")
    public List<Notes> listAll(Notes notes) {
    	if(notes.getPageIndex()!=null)
            notes.setStart((notes.getPageIndex()-1)*notes.getPageSize());
        return notesService.listAll(notes);
    }

	@RequestMapping("total")
    public Integer total(Notes notes) {

        return notesService.total(notes);
    }

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @RequestMapping("getById")
    public Notes getById(Integer id) {
        return notesService.getById(id);
    }    
     
    /**
     * 新增，忽略null字段
     *
     * @param notes 新增的记录
     * @return 返回影响行数
     */
    @RequestMapping("add")
    public int insert(Notes notes) {
        return notesService.insertIgnoreNull(notes);
    }    
      
    /**
     * 修改，忽略null字段
     *
     * @param notes 修改的记录
     * @return 返回影响行数
     */
    @RequestMapping("update")
    public int update(Notes notes) {
        return notesService.updateIgnoreNull(notes);
    }
    
    /**
     * 删除记录
     *
     * @param notes 待删除的记录
     * @return 返回影响行数
     */
    @RequestMapping("del")
    public int delete(Notes notes) {
        return notesService.delete(notes);
    }
    
}