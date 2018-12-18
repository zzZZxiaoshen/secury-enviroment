package com.marsfood.emus;

public enum OrderStatus {
    /**
     * 已经支付
     */
    UNPAI(1, "待支付"),
    /**
     * 未支付
     */
    PAI(2, "已经支付");

    public static OrderStatus getByCode(int code) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.code == code) {
                return orderStatus;
            }
        }
        return null;
    }

    OrderStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private int code;
    private String desc;

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
