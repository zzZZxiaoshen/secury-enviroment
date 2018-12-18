package com.marsfood.base;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单信息
 * @author huangxingguang
 * @date 2018/12/04
 */
public abstract class BaseDefaultOrder extends AbstractBase {

    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 订单日期
     */
    private Date orderDate;
    /*
     *配送时间
     */
    private String deliveryTime;
    /**
     * 订单总额
     */
    private BigDecimal totalPrice;
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
    private String customerName;
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
    private String buyerMark;
    /**
     * 订单备注
     */
    private String remark;
    /**
     * http://xxxx, http://xxxx’
     */
    private String voucher;
    /**
     * 线路号
     */
    private Integer routeNo;
    /**
     * 状态 1-待支付 2-已付款
     */
    private Integer status;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getBuyerMark() {
        return buyerMark;
    }

    public void setBuyerMark(String buyerMark) {
        this.buyerMark = buyerMark;
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

    public Integer getRouteNo() {
        return routeNo;
    }

    public void setRouteNo(Integer routeNo) {
        this.routeNo = routeNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
