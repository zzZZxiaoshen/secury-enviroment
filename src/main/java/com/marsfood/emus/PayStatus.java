package com.marsfood.emus;

/**
 *  订单得支付状态
 * @author shenkai
 * @date 2018/12/18
 */
public enum PayStatus {

    PAY(1, "已经支付"),
    NOT_PAY(2,"未支付");

    public static PayStatus getByCode(int code){
        PayStatus[] values = PayStatus.values();
        for (PayStatus payStatus : values) {
            if (payStatus.getCode()==code) {
                return payStatus;
            }
        }
        return null;
    }
    int code;
    String desc;

    PayStatus(int code ,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
