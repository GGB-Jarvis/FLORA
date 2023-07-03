package com.num.shop.entity;

/*
* 购物车
* */
public class ShoppingCar {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer count;

    @Override
    public String toString() {
        return "ShoppingCar{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", count=" + count +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
