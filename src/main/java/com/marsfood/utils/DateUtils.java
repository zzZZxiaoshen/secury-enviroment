package com.marsfood.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * @author huangxingguang
 * @date 2018/09/21
 */
public class DateUtils {

    private static final FastDateFormat FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

    /**
     * 字符串转时间
     * @param str 时间字符串
     * @return 转换后的时间
     */
    public static Date str2Date(String str) {
        return str2Date(str, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 字符串转时间
     * @param date 时间字符串
     * @return 转换后的时间
     */
    public static Date str2Date(String date, String format) {
        if (StringUtils.isBlank(date) || StringUtils.isBlank(format)) {
            return null;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            dateFormat.setLenient(false);
            return dateFormat.parse(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 时间转字符串
     * @param date 要转换的时间
     * @return 转换后字符串
     */
    public static String date2Str(Date date) {
        if (date == null) {
            return null;
        }
        return FORMAT.format(date);
    }

    /**
     * 时间转字符串
     * @param date 要转换的时间
     * @return 转换后字符串
     */
    public static String date2Str(Date date, String format) {
        if (date == null) {
            return null;
        }
        return FastDateFormat.getInstance(format).format(date);
    }


}
