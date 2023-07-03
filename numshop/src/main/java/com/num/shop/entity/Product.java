package com.num.shop.entity;


/**
 * 商品
 */
public class Product {
	/** ID */
	private Integer id;
	/** 商品名称 */
	private String name;
	/** 限购数量 */
	private Integer count;
	/** 封面图片 */
	private String imageurl;
	/** 详情图片 */
	private String otherurl;
	/** 标签 */
	private String tag;
	/** 创建时间 */
	private String createtime;
	/** 售卖时间 */
	private String shoptime;
	/** 用户 */
	private Integer userid;
	/** 预留 */
	private String price;
	/** 预留 */
	private String isdelete;

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}

	private String buyText;

	public String getBuyText() {
		return buyText;
	}

	public void setBuyText(String buyText) {
		this.buyText = buyText;
	}

	private String tokenId;
	private String description;
	private Integer start;
	private Integer pageIndex;
	private Integer pageSize;

	private String status;



	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private User user;


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setCount(Integer count) {
		this.count = count;
	}
	
	public Integer getCount() {
		return this.count;
	}
	
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	
	public String getImageurl() {
		return this.imageurl;
	}
	
	public void setOtherurl(String otherurl) {
		this.otherurl = otherurl;
	}
	
	public String getOtherurl() {
		return this.otherurl;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public String getTag() {
		return this.tag;
	}
	
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	public String getCreatetime() {
		return this.createtime;
	}
	
	public void setShoptime(String shoptime) {
		this.shoptime = shoptime;
	}
	
	public String getShoptime() {
		return this.shoptime;
	}
	
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
	public Integer getUserid() {
		return this.userid;
	}


	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        Product that = (Product) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Product{" +
				"id=" + id +
						",name='" + name + "'" + 
						",count='" + count + "'" + 
						",imageurl='" + imageurl + "'" + 
						",otherurl='" + otherurl + "'" + 
						",tag='" + tag + "'" + 
						",createtime='" + createtime + "'" + 
						",shoptime='" + shoptime + "'" + 
						",userid='" + userid + "'" + 
						",reserved='" + price + "'" +
						",reserved2='" + isdelete + "'" +
		                '}';
    }
	
}