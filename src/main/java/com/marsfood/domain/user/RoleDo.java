package com.marsfood.domain.user;


import com.marsfood.entity.PermissionEntity;

import java.util.List;

/**
 * @author huangxingguang
 * @date 2018/10/23
 */
public class RoleDo {

    /**
     * 角色id
     */
    private Integer id;

    /**
     * 角色名字
     */
    private String roleName;

    /**
     * 角色关键字
     */
    private String roleKey;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 角色状态
     */
    private Integer status;

    /**
     * 是否拥有该角色
     */
    private Integer own;

    /**
     * 权限permissionEntity
     */
    private List<PermissionEntity> permissions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public List<PermissionEntity> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionEntity> permissions) {
        this.permissions = permissions;
    }

    public Integer getOwn() {
        return own;
    }

    public void setOwn(Integer own) {
        this.own = own;
    }
}
