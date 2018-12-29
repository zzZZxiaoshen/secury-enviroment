package com.marsfood.entity.user;

/**
 * 角色查询条件
 * @author shenkai
 * @date 2018/11/08
 */
public class RoleQueryEntity extends QueryParam{
    /**
     * 查询角色名
     */
    private String roleName;
    /**
     * 管理员用户id
     */
    private Integer userId;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
