package com.num.shop;

import com.num.shop.entity.ShoppingCar;
import com.num.shop.entity.User;
import com.num.shop.mapper.ShoppingCarMapper;
import com.num.shop.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NumshopApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Autowired
    ShoppingCarMapper shoppingCarMapper;
    @Test
    void contextLoads() {
        shoppingCarMapper.listAll("5").forEach(System.out::println);
    }

}
