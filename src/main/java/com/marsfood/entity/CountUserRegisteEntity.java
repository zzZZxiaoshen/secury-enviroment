package com.marsfood.entity;

import java.util.Date;

/**
 * 统计用户注册人数
 * @author shenkai
 * @date 2018/12/14
 */
public class CountUserRegisteEntity {

    /**
     * 用户注册日期
     */
    private String registeDate;
    /**
     * 对应日期的用户注册数量
     */
    private Integer countUser;

    /**
     *所有用户注册数量
     */
    private Integer allUser;

    public String getRegisteDate() {
        return registeDate;
    }

    public void setRegisteDate(String registeDate) {
        this.registeDate = registeDate;
    }

    public Integer getCountUser() {
        return countUser;
    }

    public void setCountUser(Integer countUser) {
        this.countUser = countUser;
    }

    public Integer getAllUser() {
        return allUser;
    }

    public void setAllUser(Integer allUser) {
        this.allUser = allUser;
    }

}
