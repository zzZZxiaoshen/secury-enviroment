package com.marsfood.dto.response;

import java.io.Serializable;

/**
 * 服务层统一返回代码
 * @author zhuhongxin
 * @date 2018/08/26
 */
public enum ResponseCode  implements Serializable {

    /**
     * 处理成功
     */
    SUCCESS(1, "处理成功"),
    /**
     * 业务异常
     */
    BIZ_ERROR(-1, "业务异常"),
    /**
     * 系统异常
     */
    SYS_ERROR(-2, "系统异常"),
    /**
     * 不存在
     */
    NOT_EXISTS(-3, "不存在"),
    /**
     * 参数不合法
     */
    ILLEGAL(-4, "参数不合法"),
    /**
     * 缺少必要参数
     */
    PARAMETERS_REQUIRED(-5, "缺少必要参数"),
    /**
     * 用户提示
     */
    NOTICE(-6, "用户提示"),
    /**
     * 已存在
     */
    EXISTS(-7, "已存在");

    private int code;
    private String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }


}
