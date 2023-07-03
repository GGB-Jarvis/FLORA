package com.num.shop.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.num.shop.util.Blockchain;
import com.num.shop.util.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.num.shop.entity.Product;
import com.num.shop.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    @RequestMapping("list")
    public List<Product> listAll(Product product) {
//        product.setReserved2("1");
    	if(product.getPageIndex()!=null)
            product.setStart((product.getPageIndex()-1)*product.getPageSize());
        return productService.listAll(product);
    }

    /*@RequestMapping("list2")
    public List<Product> listAll(Product product,String status) {
        if(product.getPageIndex()!=null)
            product.setStart((product.getPageIndex()-1)*product.getPageSize());
        return productService.listAll2(product);
    }*/

	@RequestMapping("total")
    public Integer total(Product product) {
//        product.setReserved2("1");
        return productService.total(product);
    }

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @RequestMapping("getById")
    public Product getById(Integer id) {
        Product p = productService.getById(id);
        long now = System.currentTimeMillis();
        try {
            long shop = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(p.getShoptime()).getTime();
            if(shop>now){
                p.setBuyText("即将开售");
            }else{
                if(p.getCount()>0){
                    p.setBuyText("立即购买");
                }else{
                    p.setBuyText("已售馨");
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return p;
    }    
     
    /**
     * 新增，忽略null字段
     *
     * @param product 新增的记录
     * @return 返回影响行数
     */
    @RequestMapping("add")
    public int insert(Product product) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        product.setCreatetime(simpleDateFormat.format(new Date()));
        String tokenId = null;
//        if(product.getReserved3().equals("音乐")){
//            tokenId = HttpRequest.createNft(Blockchain.address, Blockchain.password, Blockchain.collectAddress, product.getReserved4(),
//                    product.getCount() + "", product.getName(), product.getDescription(), product.getAuthor(), product.getAuthorDesc());
//        }else{
//            tokenId = HttpRequest.createNft(Blockchain.address, Blockchain.password, Blockchain.collectAddress, product.getImageurl(),
//                    product.getCount() + "", product.getName(), product.getDescription(), product.getAuthor(), product.getAuthorDesc());
//        }
        product.setTokenId(tokenId);
        return productService.insertIgnoreNull(product);
    }    
      
    /**
     * 修改，忽略null字段
     *
     * @param product 修改的记录
     * @return 返回影响行数
     */
    @RequestMapping("update")
    public int update(Product product) {
        return productService.updateIgnoreNull(product);
    }
    
    /**
     * 删除记录
     *
     * @param product 待删除的记录
     * @return 返回影响行数
     */
    @RequestMapping("del")
    public int delete(Product product) {
        return productService.delete(product);
    }
    
}