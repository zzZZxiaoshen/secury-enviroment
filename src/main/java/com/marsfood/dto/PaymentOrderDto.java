package com.marsfood.dto;


import com.marsfood.base.BaseDefaultPaymentOrder;

/**
 * 支付单信息
 * @author huangxingguang
 * @date 2018/12/04
 */
public class PaymentOrderDto extends BaseDefaultPaymentOrder {
    /**
     * 订单的备注
     */
    private String remark;
    /**
     * 交易凭证
     */
    private String voucher;

    /**
     * 操作人Id
     */
    private Integer operator;
    /**
     * 操作人名称
     */
    private String operatorName;

    /**
     * 支付用户Id
     */
    private String openId;


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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }
}
