package com.marsfood.entity.response;

/**
 * 请求接口状态码
 * @author zhuhongxin
 * @date 2018/08/22
 */
public enum HttpBizCode {

	/**
	 * 成功
	 */
	SUCCESS(200, "成功"),
	/**
	 * 未登录
	 */
	NOT_LOGIN(201, "未登录"),
	/**
	 * 参数不合法
	 */
	ILLEGAL(202, "参数不合法"),
	/**
	 * 用户提示
	 */
	NOTICE(203, "用户提示"),
	/**
	 * 业务异常
	 */
	BIZ_ERROR(204, "业务异常"),
	/**
	 * 系统异常
	 */
	SYS_ERROR(205, "系统异常"),
	/**
	 * 账号不存在
	 */
	ACCOUNT_NOT_EXIST(209, "账号不存在"),
	/**
	 * 密码错误
	 */
	PWD_ERROR(210, "密码错误"),
	/**
	 * 已过期
	 */
	EXPIRED(215, "已过期");

	private int code;
	private String message;

	HttpBizCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
