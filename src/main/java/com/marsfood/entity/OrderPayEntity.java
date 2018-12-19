package com.marsfood.entity;

import java.math.BigDecimal;

/**
 * 订单支付实体
 * @author zhuhongxin
 * @date 2018/12/04
 */
public class OrderPayEntity {

    /**
     * 支付渠道
     */
    private Integer channel;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 支付金额
     */
    private BigDecimal price;
    /**
     * 支付用户小程序Id
     */
    private String openId;

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
