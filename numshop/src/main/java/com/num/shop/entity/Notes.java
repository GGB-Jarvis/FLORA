package com.num.shop.entity;


/**
 * 短信
 */
public class Notes {
	/** ID */
	private Integer id;
	/** 内容 */
	private String content;
	/** 发送时间 */
	private String createtime;
	/** 发送手机 */
	private String phone;
	/** 有效期 */
	private Integer validitytime;
	/** 类型 */
	private String type;
	private Integer start;
	private Integer pageIndex;
	private Integer pageSize;
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
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	public String getCreatetime() {
		return this.createtime;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public void setValiditytime(Integer validitytime) {
		this.validitytime = validitytime;
	}
	
	public Integer getValiditytime() {
		return this.validitytime;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
	

	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        Notes that = (Notes) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Notes{" +
				"id=" + id +
						",content='" + content + "'" + 
						",createtime='" + createtime + "'" + 
						",phone='" + phone + "'" + 
						",validitytime='" + validitytime + "'" + 
						",type='" + type + "'" + 
		                '}';
    }
	
}