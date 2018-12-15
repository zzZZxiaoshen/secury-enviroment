package com.marsfood.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * 扩展BeanUtils
 * @author zhuhongxin
 * @date 2018/08/22
 */
public class BeanHelper {

    /**
     * 转换单个对象
     */
    public static <S, T> T convertBean(S source, Supplier<T> supplier) {
        if (source == null) {
            return null;
        }
        T target = supplier.get();
        BeanUtils.copyProperties(source, target);
        return target;
    }

    /**
     * 转换对象列表
     */
    public static <S, T> List<T> convertBeans(List<S> source, Supplier<T> supplier) {
        List<T> target = new ArrayList<>();
        if (CollectionUtils.isEmpty(source)) {
            return target;
        }
        for (S aSource : source) {
            T targetEntity = supplier.get();
            convertBean(aSource, targetEntity);
            target.add(targetEntity);
        }
        return target;
    }

    private static <S, T> void convertBean(S source, T target) {
        BeanUtils.copyProperties(source, target);
    }

    /**
     * 深复制，返回对象
     */
    public static <T> T deepCopy(Object obj, Class<T> clazz) {
        if (obj == null || clazz == null) {
            return null;
        }
        return JSON.parseObject(JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd HH:mm:ss"), clazz);
    }

    /**
     * 深复制，返回集合
     */
    public static <T> List<T> deepCopys(Object obj, Class<T> clazz) {
        if (obj == null || clazz == null) {
            return null;
        }
        return JSON.parseArray(JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd HH:mm:ss"), clazz);
    }

}
