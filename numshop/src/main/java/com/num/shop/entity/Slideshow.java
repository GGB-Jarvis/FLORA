package com.num.shop.entity;


/**
 * 轮播图
 */
public class Slideshow {
	/** ID */
	private Integer id;
	/** 图片地址 */
	private String imageurl;
	/** 状态 */
	private Integer status;
	/** 创建时间 */
	private String createtime;
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
	
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	
	public String getImageurl() {
		return this.imageurl;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	public String getCreatetime() {
		return this.createtime;
	}
	

	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        Slideshow that = (Slideshow) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Slideshow{" +
				"id=" + id +
						",imageurl='" + imageurl + "'" + 
						",status='" + status + "'" + 
						",createtime='" + createtime + "'" + 
		                '}';
    }
	
}