package com.marsfood.domain.user;


import com.marsfood.entity.user.RoleEntity;

/**
 * 管理员用户权限
 * @author shenkai
 * @date 2018/11/14
 */
public class AdminUserPermissionDo {
    /**
     * 角色信息
     */
    private RoleEntity roleEntity;
    /**
     * 是否拥有这个角色
     */
    private boolean own;

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }

    public boolean isOwn() {
        return own;
    }

    public void setOwn(boolean own) {
        this.own = own;
    }
}
