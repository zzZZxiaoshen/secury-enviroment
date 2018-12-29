package com.marsfood.entity.user;

/**
 * 管理员用户
 * @author huangxingguang
 * @date 2018/10/29
 */
public class AdminUserEntity  {

    /**
     * 管理员id
     *
     * */
    private Integer id;
    /**
     * 管理员名称
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
     * 管理员旧密码
     */
    private String oldPassword;
    /**
     * 管理员昵称
     */
    private String nickName;
    /**
     * 头像地址
     */
    private String portrait;
    /**
     * 管理员状态
     */
    private Integer status;
    /**
     * 状态描述
     */
    private String statusDesc;
    /**
     * 创建时间
     */
    private String gmtCreated;
    /**
     * 最后修改时间
     */
    private String gmtModified;

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

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(String gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
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
