package com.marsfood.domain.user;

/**
 * 角色权限映射
 * @author huangxingguang
 * @date 2018/11/12
 */
public class RolePermissionDo {

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 权限id
     */
    private Integer permissionId;
    /**
     * 角色名字
     */
    private String name;
    /**
     * 是否包含该权限
     */
    private boolean own;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
