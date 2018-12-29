package com.marsfood.domain.user;

/**
 * 权限实体类
 * @author huangxingguang
 * @date 2018/11/14
 */
public class PermissionDo {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限介绍
     */
    private String description;
    /**
     * 资源
     */
    private String resource;

    /**
     * 非数据库字段，如用角色拥有>1
     */
    private Integer own;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public Integer getOwn() {
        return own;
    }

    public void setOwn(Integer own) {
        this.own = own;
    }
}

