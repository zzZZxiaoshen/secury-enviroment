package com.marsfood.dto.user;

import com.marsfood.entity.QueryParam;

import java.io.Serializable;

/**
 * 管理员查询条件
 * @author huangxingguang
 * @date 2018/11/14
 */
public class AdminUserQueryDto extends QueryParam implements Serializable {


    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickName;

    public AdminUserQueryDto() {
    }

    public AdminUserQueryDto(Integer id) {
        this.setId(id);
    }

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
