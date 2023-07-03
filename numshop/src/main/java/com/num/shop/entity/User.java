package com.num.shop.entity;


/**
 * 用户
 */
public class User {
	/** ID */
	private Integer id;
	/** 用户名 */
	private String username;
	/** 密码 */
	private String password;
	/** 昵称 */
	private String realname;
	/** openid */
	private String openid;
	/** 头像 */
	private String imageurl;
	/** 真实姓名 */
	private String name;
	/** 电话号码 */
	private String phone;
	/** 身份证号码 */
	private String cardno;
	/** 是否认证 */
	private Integer authentication;
	/** 注册时间 */
	private String registertime;
	/** 认证时间 */
	private String authenticationtime;
	/** 预留 */
	private String reserved;
	private String address;
	private String privateKey;
	private String blockpassword;
	private Integer start;
	private Integer pageIndex;
	private Integer pageSize;

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getBlockpassword() {
		return blockpassword;
	}

	public void setBlockpassword(String blockpassword) {
		this.blockpassword = blockpassword;
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
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setRealname(String realname) {
		this.realname = realname;
	}
	
	public String getRealname() {
		return this.realname;
	}
	
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	public String getOpenid() {
		return this.openid;
	}
	
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	
	public String getImageurl() {
		return this.imageurl;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	
	public String getCardno() {
		return this.cardno;
	}
	
	public void setAuthentication(Integer authentication) {
		this.authentication = authentication;
	}
	
	public Integer getAuthentication() {
		return this.authentication;
	}
	
	public void setRegistertime(String registertime) {
		this.registertime = registertime;
	}
	
	public String getRegistertime() {
		return this.registertime;
	}
	
	public void setAuthenticationtime(String authenticationtime) {
		this.authenticationtime = authenticationtime;
	}
	
	public String getAuthenticationtime() {
		return this.authenticationtime;
	}
	
	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
	
	public String getReserved() {
		return this.reserved;
	}


	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        User that = (User) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "User{" +
				"id=" + id +
						",username='" + username + "'" + 
						",password='" + password + "'" + 
						",realname='" + realname + "'" + 
						",openid='" + openid + "'" + 
						",imageurl='" + imageurl + "'" + 
						",name='" + name + "'" + 
						",phone='" + phone + "'" + 
						",cardno='" + cardno + "'" + 
						",authentication='" + authentication + "'" + 
						",registertime='" + registertime + "'" + 
						",authenticationtime='" + authenticationtime + "'" + 
						",reserved='" + reserved + "'" +
						",token='" + token + "'" +
		                '}';
    }
	
}