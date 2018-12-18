package com.marsfood.base;

import java.math.BigDecimal;

/**
 * 订单详情
 * @author huangxingguang
 * @date 2018/12/4
 */
public abstract class BaseDefaultOrderDetail extends AbstractBase {
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品数量
     */
    private Integer count;
    /**
     * 单位
     */
    private String unit;
    /**
     * 重量
     */
    private Double weight;
    /**
     * 库存单位
     */
    private String skuNo;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 总价
     */
    private BigDecimal totalPrice;
    /**
     * 订单号
     */
    private String orderNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getSkuNo() {
        return skuNo;
    }

    public void setSkuNo(String skuNo) {
        this.skuNo = skuNo;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
