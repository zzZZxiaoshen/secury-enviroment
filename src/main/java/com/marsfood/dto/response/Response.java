package com.marsfood.dto.response;

import java.io.Serializable;

/**
 * 服务层统一返回对象
 * @author zhuhongxin
 * @date 2018/08/26
 */
public class Response<T> implements Serializable {

    private static final long serialVersionUID = -2636120947210433651L;
    /**
     * 结果
     */
    private T result;
    /**
     * 消息
     */
    private String message;
    /**
     * 状态码
     */
    private ResponseCode code;

    public Response() {

    }

    public Response<T> fill(ResponseCode code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
        return this;
    }

    public static <T> Response<T> newInstance() {
        return new Response<>();
    }

    public T getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code.getCode();
    }

    public ResponseCode getCodeProperty(){
        return code;
    }

}
