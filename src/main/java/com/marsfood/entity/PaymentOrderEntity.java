package com.marsfood.entity;


import com.marsfood.base.BaseDefaultPaymentOrder;

/**
 * @author huangxingguang
 * @date 2018/12/4
 */
public class PaymentOrderEntity extends BaseDefaultPaymentOrder {
    /**
     * 订单的备注
     */
    private String remark;
    /**
     * 交易凭证
     */
    private String voucher;

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
}
