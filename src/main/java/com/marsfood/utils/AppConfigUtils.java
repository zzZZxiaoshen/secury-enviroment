package com.marsfood.utils;

import java.util.PropertyResourceBundle;

/**
 * 应用配置文件工具
 * @author zhuhongxin
 * @date 2018/09/26
 */
public class AppConfigUtils {

	private final static PropertyResourceBundle CONFIG = (PropertyResourceBundle) PropertyResourceBundle.getBundle("properties/app");

	private static String getString(String property) {
		String value = null;
		if (CONFIG.containsKey(property)) {
			value = CONFIG.getString(property);
		}
		return value;
	}

	public static final String USERNAME = AppConfigUtils.getString("app.username");
	public static final String PASSWORD = AppConfigUtils.getString("app.password");
	public static final String SECRET_KEY = AppConfigUtils.getString("app.secret");

}
