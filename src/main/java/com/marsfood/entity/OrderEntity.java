package com.marsfood.entity;


import com.marsfood.base.BaseDefaultOrder;

import java.util.List;

/**
 * 订单
 * @author huangxingguang
 * @date 2018/12/4
 */
public class OrderEntity extends BaseDefaultOrder {

    /**
     * 已支付的订单信息
     */
    private PaymentOrderEntity paymentOrder;
    /**
     * 订单明细
     */
    private List<OrderDetailEntity> orderDetails;
    /*
    *
    */

    public PaymentOrderEntity getPaymentOrder() {
        return paymentOrder;
    }

    public void setPaymentOrder(PaymentOrderEntity paymentOrder) {
        this.paymentOrder = paymentOrder;
    }

    public List<OrderDetailEntity> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailEntity> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
