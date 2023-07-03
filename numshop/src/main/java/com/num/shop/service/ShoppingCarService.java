package com.num.shop.service;

import com.num.shop.entity.ShoppingCar;
import com.num.shop.mapper.ShoppingCarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCarService {

    @Autowired
    private ShoppingCarMapper shoppingCarMapper;

    public List<ShoppingCar> listAll(String userid) {
        return shoppingCarMapper.listAll(userid);
    }

    // 删除商品
    public int delete(String pid,String userid) {
        return shoppingCarMapper.delete(pid,userid);
    }
    // 添加商品到购物车
    public int add(ShoppingCar shoppingCar) {
        return shoppingCarMapper.insert(shoppingCar);
    }
}
