package com.num.shop.entity;


import java.util.List;

/**
 * 订单
 */
public class Orders {
	/** ID */
	private Integer id;
	/** 购买者 */
	private Integer userid;
	/** 购买时间 */
	private String createtime;
	/** 支付时间 */
	private String paytime;
	/** 支付方式 */
	private String paytype;
	/** 支付状态 */
	private String status;
	private String reserved;
	private String reserved2;
	private String reserved3;
	private String reserved4;
	private String reserved5;
	private Integer start;
	private Integer pageIndex;
	private Integer pageSize;

	private List<Details> list;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Details> getList() {
		return list;
	}

	public void setList(List<Details> list) {
		this.list = list;
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
	
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
	public Integer getUserid() {
		return this.userid;
	}
	
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	public String getCreatetime() {
		return this.createtime;
	}
	
	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}
	
	public String getPaytime() {
		return this.paytime;
	}
	
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	
	public String getPaytype() {
		return this.paytype;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
	
	public String getReserved() {
		return this.reserved;
	}
	
	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}
	
	public String getReserved2() {
		return this.reserved2;
	}
	
	public void setReserved3(String reserved3) {
		this.reserved3 = reserved3;
	}
	
	public String getReserved3() {
		return this.reserved3;
	}
	
	public void setReserved4(String reserved4) {
		this.reserved4 = reserved4;
	}
	
	public String getReserved4() {
		return this.reserved4;
	}
	
	public void setReserved5(String reserved5) {
		this.reserved5 = reserved5;
	}
	
	public String getReserved5() {
		return this.reserved5;
	}
	

	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        Orders that = (Orders) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Orders{" +
				"id=" + id +
						",userid='" + userid + "'" + 
						",createtime='" + createtime + "'" + 
						",paytime='" + paytime + "'" + 
						",paytype='" + paytype + "'" + 
						",status='" + status + "'" + 
						",reserved='" + reserved + "'" + 
						",reserved2='" + reserved2 + "'" + 
						",reserved3='" + reserved3 + "'" + 
						",reserved4='" + reserved4 + "'" + 
						",reserved5='" + reserved5 + "'" + 
		                '}';
    }
	
}