package com.marsfood.Service;

import com.marsfood.dto.response.Response;
import com.marsfood.dto.user.AdminUserDto;
import com.marsfood.dto.user.PermissionDto;
import com.marsfood.dto.user.RoleDto;

import java.util.List;

/**
 * 用户service层
 * @author shenkai
 * @date 2019/1/2
 */
public class AdminUserService {

    /**
    * 根据用户查询权限
    */
    public Response<List<PermissionDto>> findPermissionByAdminUserId(int userId) {
        return null;
    }



    /**
    * 根据用户id查询角色
    */
    public Response<List<RoleDto>> findRoleByAdminUserId(int userId) {
        return null;
    }

    /**
    * 根据用户名查查找用户
    */
    public Response<AdminUserDto> findUserByUsername(String username) {
        return null;
    }
}
