package com.marsfood.entity;

import java.math.BigDecimal;
import java.util.Date;

public class OrderEntity {
    /*
        主键Id
    */
    private Integer id;

    /*
        订单号
    */
    private String orderNo;

    /*
        订单日期
    */
    private Date orderDate;

    /*
        配送时间
    */
    private String deliveryTime;

    /*
        订单总额
    */
    private BigDecimal totalPrice;

    /*
        折扣
    */
    private BigDecimal discount;

    /*
        支付金额
    */
    private BigDecimal paid;

    /*
        客户名称
    */
    private String customerName;

    /*
        收货人
    */
    private String receiver;

    /*
        手机号
    */
    private String mobile;

    /*
        省份
    */
    private String province;

    /*
        城市
    */
    private String city;

    /*
        区域
    */
    private String area;

    /*
        详细地址
    */
    private String address;

    /*
        购买人备注
    */
    private String buyerMark;

    /*
        订单备注
    */
    private String remark;

    /*
        ‘http://xxxx’, ‘http://xxxx’
    */
    private String voucher;

    /*
        线路号
    */
    private Byte routeNo;

    /*
        状态 1-待支付 2-已付款
    */
    private Boolean status;

    /*
        创建时间
    */
    private Date gmtCreated;

    /*
        最后修改时间
    */
    private Date gmtModified;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryTime() {
        return this.deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public BigDecimal getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getDiscount() {
        return this.discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getPaid() {
        return this.paid;
    }

    public void setPaid(BigDecimal paid) {
        this.paid = paid;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getReceiver() {
        return this.receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBuyerMark() {
        return this.buyerMark;
    }

    public void setBuyerMark(String buyerMark) {
        this.buyerMark = buyerMark;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getVoucher() {
        return this.voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public Byte getRouteNo() {
        return this.routeNo;
    }

    public void setRouteNo(Byte routeNo) {
        this.routeNo = routeNo;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getGmtCreated() {
        return this.gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return this.gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

}

