package com.marsfood.dto.user;

import com.marsfood.entity.QueryParam;

/**
 * 角色查询条件
 * @author huangxingguang
 * @date 2018/11/15
 */
public class RoleQueryDto extends QueryParam {

    /**
     * 查询角色名
     */
    private String roleName;


    public RoleQueryDto() {

    }

    public RoleQueryDto(Integer id) {
        this.setId(id);
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
