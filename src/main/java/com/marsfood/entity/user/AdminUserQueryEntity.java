package com.marsfood.entity.user;

import com.marsfood.entity.QueryParam;

/**
 * 管理员查询条件
 * @author huangxingguang
 * @date 2018/11/14
 */
public class AdminUserQueryEntity extends QueryParam {


    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

}
