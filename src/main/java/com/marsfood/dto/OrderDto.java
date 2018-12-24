package com.marsfood.dto;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDto {
    /**
	* 主键Id
	*/
    private Integer id;

    /**
	* 订单号
	*/
    private String orderno;

    /**
	* 订单日期
	*/
    private Date orderdate;

    /**
	* 配送时间
	*/
    private String deliverytime;

    /**
	* 订单总额
	*/
    private BigDecimal totalprice;

    /**
	* 折扣
	*/
    private BigDecimal discount;

    /**
	* 支付金额
	*/
    private BigDecimal paid;

    /**
	* 客户名称
	*/
    private String customername;

    /**
	* 收货人
	*/
    private String receiver;

    /**
	* 手机号
	*/
    private String mobile;

    /**
	* 省份
	*/
    private String province;

    /**
	* 城市
	*/
    private String city;

    /**
	* 区域
	*/
    private String area;

    /**
	* 详细地址
	*/
    private String address;

    /**
	* 购买人备注
	*/
    private String buyermark;

    /**
	* 订单备注
	*/
    private String remark;

    /**
	* ‘http://xxxx’, ‘http://xxxx’
	*/
    private String voucher;

    /**
	* 线路号
	*/
    private Byte routeno;

    /**
	* 状态 1-待支付 2-已付款
	*/
    private Boolean status;

    /**
	* 创建时间
	*/
    private Date gmtcreated;

    /**
	* 最后修改时间
	*/
    private Date gmtmodified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public String getDeliverytime() {
        return deliverytime;
    }

    public void setDeliverytime(String deliverytime) {
        this.deliverytime = deliverytime;
    }

    public BigDecimal getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getPaid() {
        return paid;
    }

    public void setPaid(BigDecimal paid) {
        this.paid = paid;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBuyermark() {
        return buyermark;
    }

    public void setBuyermark(String buyermark) {
        this.buyermark = buyermark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public Byte getRouteno() {
        return routeno;
    }

    public void setRouteno(Byte routeno) {
        this.routeno = routeno;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getGmtcreated() {
        return gmtcreated;
    }

    public void setGmtcreated(Date gmtcreated) {
        this.gmtcreated = gmtcreated;
    }

    public Date getGmtmodified() {
        return gmtmodified;
    }

    public void setGmtmodified(Date gmtmodified) {
        this.gmtmodified = gmtmodified;
    }
}