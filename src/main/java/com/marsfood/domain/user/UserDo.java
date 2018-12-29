package com.marsfood.domain.user;

import java.util.Date;

/**
 * 用户表
 * @author huangxingguang
 * @date 2018/10/01
 **/
public class UserDo {
    /**
     * 用户id
     */
    private Integer id;
    /**
     * 微信小程序Id
     */
    private String appId;
    /**
     * 微信用户Id
     */
    private String openId;
    /**
     * 用户唯一编号
     */
    private String unionId;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户头像
     */
    private String portrait;
    /**
     * 用户电话
     */
    private String mobile;
    /**
     * 火币数量
     */
    private Integer integral;
    /**
     * 创建时间
     */
    private Date gmtCreated;
    /**
     * 修改时间
     */
    private Date gmtModified;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }


    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }


    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }
}
