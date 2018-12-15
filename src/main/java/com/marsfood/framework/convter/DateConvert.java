package com.marsfood.framework.convter;

import com.marsfood.utils.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 全局日期处理类
 * @author huangxingguang
 * @date 2018/11/19
 */
public class DateConvert implements Converter<String, Date> {

    /**
     * 日期格式
     */
    private static final List<String> FORMATS = new ArrayList<>(9);

    static {
        FORMATS.add("yyyy");
        FORMATS.add("yyyy-MM");
        FORMATS.add("yyyy-MM-dd");
        FORMATS.add("yyyy-MM-dd HH:mm");
        FORMATS.add("yyyy-MM-dd HH:mm:ss");
        FORMATS.add("yyyy/MM");
        FORMATS.add("yyyy/MM/dd");
        FORMATS.add("yyyy/MM/dd HH:mm");
        FORMATS.add("yyyy/MM/dd HH:mm:ss");
    }

    @Override
    public Date convert(String source) {
        // 2017
        if (source.matches("^\\d{4}$")) {
            return parseDate(source, FORMATS.get(0));
        // 2017-09
        } else if (source.matches("^\\d{4}-\\d{1,2}$")) {
            return parseDate(source, FORMATS.get(1));
        // 2017-09-10
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
            return parseDate(source, FORMATS.get(2));
        // 2017-09-10 21:15
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")) {
            return parseDate(source, FORMATS.get(3));
        // 2017-09-10 21:15:30
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
            return parseDate(source, FORMATS.get(4));
        // 2017/09
        } else if (source.matches("^\\d{4}/\\d{1,2}$")) {
            return parseDate(source, FORMATS.get(5));
        // 2017/09/10
        } else if (source.matches("^\\d{4}/\\d{1,2}/\\d{1,2}$")) {
            return parseDate(source, FORMATS.get(6));
        // 2017/09/10 21:15
        } else if (source.matches("^\\d{4}/\\d{1,2}/\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")) {
            return parseDate(source, FORMATS.get(7));
        // 2017/09/10 21:15:30
        } else if (source.matches("^\\d{4}/\\d{1,2}/\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
            return parseDate(source, FORMATS.get(8));
        }
        return null;
    }

    /**
     * 功能描述：格式化日期
     * @param dateStr String 字符型日期
     * @param format  String 格式
     * @return Date 日期
     */
    private Date parseDate(String dateStr, String format) {
        try {
            return DateUtils.str2Date(dateStr, format);
        } catch (Exception e1) {
            return null;
        }
    }

}


