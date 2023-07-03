package com.num.shop.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.num.shop.entity.Slideshow;
import com.num.shop.service.SlideshowService;

@RestController
@RequestMapping("/slideshow")
public class SlideshowController {

    @Autowired
    private SlideshowService slideshowService;

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    @RequestMapping("list")
    public List<Slideshow> listAll(Slideshow slideshow) {
    	if(slideshow.getPageIndex()!=null)
            slideshow.setStart((slideshow.getPageIndex()-1)*slideshow.getPageSize());
        return slideshowService.listAll(slideshow);
    }

	@RequestMapping("total")
    public Integer total(Slideshow slideshow) {

        return slideshowService.total(slideshow);
    }

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @RequestMapping("getById")
    public Slideshow getById(Integer id) {
        return slideshowService.getById(id);
    }    
     
    /**
     * 新增，忽略null字段
     *
     * @param slideshow 新增的记录
     * @return 返回影响行数
     */
    @RequestMapping("add")
    public int insert(Slideshow slideshow) {
        slideshow.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return slideshowService.insertIgnoreNull(slideshow);
    }
      
    /**
     * 修改，忽略null字段
     *
     * @param slideshow 修改的记录
     * @return 返回影响行数
     */
    @RequestMapping("update")
    public int update(Slideshow slideshow) {
        return slideshowService.updateIgnoreNull(slideshow);
    }
    
    /**
     * 删除记录
     *
     * @param slideshow 待删除的记录
     * @return 返回影响行数
     */
    @RequestMapping("del")
    public int delete(Slideshow slideshow) {
        return slideshowService.delete(slideshow);
    }
    
}