package com.num.shop.entity;

/**
 * @Auther: 21409
 * @Date: 2023/4/28 18:05
 * @Description:地址实体类
 */
public class Address {
    /**
     * 基本字段
     */
    private Integer id;
    private Integer userid;
    private String adressee;//收件人
    private String phone;
    private String completedaddress;//地址


    private Integer isdefault;

    public Integer getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Integer isdefault) {
        this.isdefault = isdefault;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", userid=" + userid +
                ", adressee='" + adressee + '\'' +
                ", phone='" + phone + '\'' +
                ", completedaddress='" + completedaddress + '\'' +
                ", isdefault='" + isdefault + '\'' +
                ", user=" + user +
                ", start=" + start +
                ", pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                '}';
    }

    /**
     * 添加联系人字段
     */
    private User user;

    /**
     * 添加字段支持分页查询
     */
    private Integer start;
    private Integer pageIndex;//页码
    private Integer pageSize;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getAdressee() {
        return adressee;
    }

    public void setAdressee(String adressee) {
        this.adressee = adressee;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompletedaddress() {
        return completedaddress;
    }

    public void setCompletedaddress(String completedaddress) {
        this.completedaddress = completedaddress;
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

    public Address(){

    }

    public Address(Integer id, Integer userid, String adressee, String phone, String completedaddress, User user, Integer start, Integer pageIndex, Integer pageSize,Integer isdefault) {
        this.id = id;
        this.userid = userid;
        this.adressee = adressee;
        this.phone = phone;
        this.completedaddress = completedaddress;
        this.isdefault = isdefault;
        this.user = user;
        this.start = start;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }
}
