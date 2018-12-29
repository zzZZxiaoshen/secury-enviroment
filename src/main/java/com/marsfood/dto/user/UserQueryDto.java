package com.marsfood.dto.user;

import com.marsfood.entity.QueryParam;

import java.io.Serializable;

/**
 * 查询用户条件
 * @author huangxingguang
 * @date 2018/11/19
 */
public class UserQueryDto extends QueryParam implements Serializable {

    private static final long serialVersionUID = 1240298347293053535L;
    /**
     * 手机号
     */
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
