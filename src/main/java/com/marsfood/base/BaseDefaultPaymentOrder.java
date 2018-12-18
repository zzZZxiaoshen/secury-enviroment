package com.marsfood.base;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单支付信息
 * @author huangxingguang
 * @date 2018/12/4
 */
public abstract class BaseDefaultPaymentOrder extends AbstractBase {

    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 支付总额
     */
    private BigDecimal payAmount;
    /**
     * 1微信 2支付宝 3汇款 4其他
     */
    private Integer payChannel;
    /**
     * 交易号
     */
    private String tradeNo;
    /**
     * 交易账户
     */
    private String tradeAccount;
    /**
     * 1-已支付 2-未支付 3-已关闭
     */
    private Integer status;
    /**
     * 支付时间
     */
    private Date gmtPaid;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(Integer payChannel) {
        this.payChannel = payChannel;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getTradeAccount() {
        return tradeAccount;
    }

    public void setTradeAccount(String tradeAccount) {
        this.tradeAccount = tradeAccount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getGmtPaid() {
        return gmtPaid;
    }

    public void setGmtPaid(Date gmtPaid) {
        this.gmtPaid = gmtPaid;
    }
}
