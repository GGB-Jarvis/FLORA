package com.num.shop.mapper;

import com.num.shop.entity.Orders;
import com.num.shop.entity.ShoppingCar;
import com.num.shop.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShoppingCarMapper {


    /**
     * 查询用户所有记录
     *
     * @return 返回集合，没有返回空List
     */
    List<ShoppingCar> listAll(String userid);


    /**
     * 新增，插入所有字段
     *
     * @param productid,userid
     * @return 返回影响行数
     */

    int insert(ShoppingCar shoppingCar);


    /**
     * 修改，修改所有字段
     *
     * @param shoppingCar 修改的记录
     * @return 返回影响行数
     */
    int update(ShoppingCar shoppingCar);

    /**
     * 修改，忽略null字段
     *
     * @param shoppingCar 修改的记录
     * @return 返回影响行数
     */
    int updateIgnoreNull(ShoppingCar shoppingCar);

    /**
     * 删除记录
     *
     * @param pid 待删除的记录id
     * @return 返回影响行数
     */
    int delete(String pid,String userid);

}
