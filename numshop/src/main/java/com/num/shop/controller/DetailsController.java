package com.num.shop.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.num.shop.entity.Details;
import com.num.shop.service.DetailsService;

@RestController
@RequestMapping("/details")
public class DetailsController {

    @Autowired
    private DetailsService detailsService;

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    @RequestMapping("list")
    public List<Details> listAll(Details details) {
    	if(details.getPageIndex()!=null)
            details.setStart((details.getPageIndex()-1)*details.getPageSize());
        return detailsService.listAll(details);
    }

    @RequestMapping("list2")
    public List<Details> listAll2(Integer userid) {

        return detailsService.listAll2(userid);
    }

	@RequestMapping("total")
    public Integer total(Details details) {

        return detailsService.total(details);
    }

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @RequestMapping("getById")
    public Details getById(Integer id) {
        return detailsService.getById(id);
    }    
     
    /**
     * 新增，忽略null字段
     *
     * @param details 新增的记录
     * @return 返回影响行数
     */
    @RequestMapping("add")
    public int insert(Details details) {
        return detailsService.insertIgnoreNull(details);
    }    
      
    /**
     * 修改，忽略null字段
     *
     * @param details 修改的记录
     * @return 返回影响行数
     */
    @RequestMapping("update")
    public int update(Details details) {
        return detailsService.updateIgnoreNull(details);
    }
    
    /**
     * 删除记录
     *
     * @param details 待删除的记录
     * @return 返回影响行数
     */
    @RequestMapping("del")
    public int delete(Details details) {
        return detailsService.delete(details);
    }
    
}