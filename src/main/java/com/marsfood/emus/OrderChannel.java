package com.marsfood.emus;

/**
 * 支付渠道枚举类
 *
 * @author shenkai
 * @date 2018/12/18
 */
public enum OrderChannel {

    ALIPAY(1, "支付宝"),
    WXPAY(2, "微信");

    OrderChannel(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static  OrderChannel getOrderChannelByCode(int code) {
        for (OrderChannel orderChannel : OrderChannel.values()) {
            if (orderChannel.getCode() == code) {
                return orderChannel;
            }
        }
        return null;
    }

    int code;
    String desc;

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
