package com.num.shop.entity;

import java.math.BigDecimal;

/**
 * 订单详情
 */
public class Details {
	/** ID */
	private Integer id;
	/** 订单 */
	private Integer orderid;
	/** 商品 */
	private Integer productid;

	private Product product;
	/** 数量 */
	private Integer count;
	/** 商品编码 */
	private String productno;
	/** 商品单价 */
	private BigDecimal price;
	private Integer start;
	private Integer pageIndex;
	private Integer pageSize;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	
	public Integer getOrderid() {
		return this.orderid;
	}
	
	public void setProductid(Integer productid) {
		this.productid = productid;
	}
	
	public Integer getProductid() {
		return this.productid;
	}
	
	public void setCount(Integer count) {
		this.count = count;
	}
	
	public Integer getCount() {
		return this.count;
	}
	
	public void setProductno(String productno) {
		this.productno = productno;
	}
	
	public String getProductno() {
		return this.productno;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public BigDecimal getPrice() {
		return this.price;
	}
	

	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        Details that = (Details) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Details{" +
				"id=" + id +
						",orderid='" + orderid + "'" + 
						",productid='" + productid + "'" + 
						",count='" + count + "'" + 
						",productno='" + productno + "'" + 
						",price='" + price + "'" + 
		                '}';
    }
	
}