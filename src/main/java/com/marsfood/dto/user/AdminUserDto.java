package com.marsfood.dto.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员用户
 * @author huangxingguang
 * @date 2018/10/29
 */
public class AdminUserDto implements Serializable {
    private static final long serialVersionUID = 770008394621527093L;

    /**
     * 管理员id
     */
    private Integer id;
    /**
     * 管理员账号
     */
    private String username;
    /**
     * 旧的管理员名称
     */
    private String oldname;
    /**
     * 管理员密码
     */
    private String password;
    /**
     * 管理员账号
     */
    private String nickName;
    /**
     * 管理员旧密码
     */
    private String oldPassword;
    /**
     * 管理员头像
     */
    private String portrait;
    /**
     * 管理员状态
     */
    private Integer status;
    /**
     *管理员创建时间
     */
    private Date gmtCreated;
    /**
     * 管理员最后修改时间
     */
    private Date gmtModified;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getOldname() {
        return oldname;
    }

    public void setOldname(String oldname) {
        this.oldname = oldname;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
