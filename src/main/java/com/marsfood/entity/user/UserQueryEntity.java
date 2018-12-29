package com.marsfood.entity.user;

import com.marsfood.entity.QueryParam;

/**
 * 查询用户条件
 * @author huangxingguang
 * @date 2018/11/19
 */
public class UserQueryEntity extends QueryParam {
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
