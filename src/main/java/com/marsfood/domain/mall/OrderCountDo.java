package com.marsfood.domain.mall;

import java.util.Date;

/**
 * 统计订单数量
 * @author shenkai
 * @date 2018/12/12
 */
public class OrderCountDo {

    /**
    * 订单总数
    */
   private   Integer allOrder;

    /**
    * 对应日期订单总数
    */
    private  Integer countOrder;

    /**
     * 订单日期
     */
    private  Date orderDate;

    public Integer getAllOrder() {
        return allOrder;
    }

    public void setAllOrder(Integer allOrder) {
        this.allOrder = allOrder;
    }

    public Integer getCountOrder() {
        return countOrder;
    }

    public void setCountOrder(Integer countOrder) {
        this.countOrder = countOrder;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
