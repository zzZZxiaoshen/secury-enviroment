package com.marsfood.dto;


import com.marsfood.base.BaseDefaultOrder;

import java.util.List;

/**
 * 订单信息
 * @author huangxingguang
 * @date 2018/12/04
 */
public class OrderDto extends BaseDefaultOrder {
    /**
     * 已支付的订单信息
     */
    private PaymentOrderDto paymentOrder;

    /**
     * 订单明细
     */
    private List<OrderDetailDto> orderDetails;
    /**
     * 操作人Id
     */
    private Integer operator;
    /**
     * 操作人名称
     */
    private String operatorName;

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public PaymentOrderDto getPaymentOrder() {
        return paymentOrder;
    }

    public void setPaymentOrder(PaymentOrderDto paymentOrder) {

        this.paymentOrder = paymentOrder;
    }

    public List<OrderDetailDto> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailDto> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
