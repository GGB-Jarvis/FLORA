package com.num.shop.controller;

import com.num.shop.entity.Product;
import com.num.shop.entity.ShoppingCar;
import com.num.shop.entity.User;
import com.num.shop.mapper.ProductMapper;
import com.num.shop.service.ShoppingCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shoppingcar")
public class ShoppingCarController {

    @Autowired
    private ShoppingCarService shoppingCarService;
    @Autowired
    private ProductMapper productMapper;
    /**
     * 查询购物车所有记录
     *
     * @return 返回集合，没有返回空List
     */
    @RequestMapping("list")
    public List<Product> listAll(String userid) {
        System.out.println(userid);
        List<ShoppingCar> list = shoppingCarService.listAll(userid);
        List<Product> list1 = new ArrayList<>();
        System.out.println(list.toString());
        for(ShoppingCar shoppingCar:list){
            Product product = productMapper.getById(shoppingCar.getProductId());
            System.out.println(product.getName());
            product.setCount(shoppingCar.getCount());
            list1.add(product);
        }
        return list1;
        }
    @RequestMapping("delete")
    public int delete(String pid,String userid){
            return shoppingCarService.delete(pid,userid);
        }

    @RequestMapping("insert")
    public int add(Integer userid,Integer productid, Integer count){
        ShoppingCar shoppingCar = new ShoppingCar();
        shoppingCar.setUserId(userid);
        shoppingCar.setProductId(productid);
        shoppingCar.setCount(count);
        return shoppingCarService.add(shoppingCar);
    }

}
