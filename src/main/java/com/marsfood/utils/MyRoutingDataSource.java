package com.marsfood.utils;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MyRoutingDataSource extends AbstractRoutingDataSource {

    public static final String DATASOURCE1 = "datasource1";
    public static final String DATASOURCE2 = "datasource2";

    /**
     * 指定得key
     * Threadlocal线程工具类：是一个线程绑定对象
     */
    private static ThreadLocal<String> DATASOURCE_KEY = new ThreadLocal<String>();


    /**
     * 设置数据源实用得key
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DATASOURCE_KEY.get();
    }

    /**
     * 设置数据源
     */
    public static  void  setKey(String key) {
        DATASOURCE_KEY.set(key);
    }

    /**
     * 因为数据源切换得问题 所以需要移除线程绑定类得key
     */
    public static  void removeKey() {
        DATASOURCE_KEY.remove();
    }

}