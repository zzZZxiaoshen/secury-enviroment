package com.marsfood.framework.shiro;

import com.marsfood.Service.AdminUserService;
import com.marsfood.domain.user.AdminUserDo;
import com.marsfood.dto.response.Response;
import com.marsfood.dto.response.ResponseCode;
import com.marsfood.dto.user.AdminUserDto;
import com.marsfood.dto.user.PermissionDto;
import com.marsfood.dto.user.RoleDto;
import com.marsfood.entity.response.HttpBizCode;
import com.marsfood.entity.user.AdminUserEntity;
import com.marsfood.utils.BeanHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 处理认证与授权的reaml
 * @author shenkai
 * @date 2019/1/2
 */
public class statelessRleam extends AuthorizingRealm {

    @Autowired
    private AdminUserService adminUserService;


    /**
     * 根据用户id获取许可
     */
    private Set<String> getPermissionByUserId(int userId) {
        Response<List<PermissionDto>> response = adminUserService.findPermissionByAdminUserId(userId);
        if (response.getCode() != ResponseCode.SUCCESS.getCode() || CollectionUtils.isEmpty(response.getResult())) {
            return Collections.emptySet();
        }
        return response.getResult().stream().map(PermissionDto::getResource).collect(Collectors.toSet());
    }

    /**
     * 根据用户id获取角色
     */
    private Set<String> getRoleByUserId(int userId) {
        Response<List<RoleDto>> response = adminUserService.findRoleByAdminUserId(userId);
        if (response.getCode() != ResponseCode.SUCCESS.getCode() || CollectionUtils.isEmpty(response.getResult())) {
            return Collections.emptySet();
        }
        return response.getResult().stream().map(RoleDto::getRoleKey).collect(Collectors.toSet());
    }
    /**
    * 授权
    */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        AdminUserEntity principal = (AdminUserEntity) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if ("admin".equals(principal.getUsername())) {
            info.addStringPermission("*:*");
            info.addRole("*:*");
        } else {
            info.addStringPermissions(getPermissionByUserId(principal.getId()));
            info.addRoles(getRoleByUserId(principal.getId()));
        }
        return null;
    }

    /**
    * 认证 注意在前后端分离的情况下的token的具体验证交给了登入接口处理了，
     * 原先我们在认证的realm中是为了将用户标识与用户名称与用户信息交给shirofilter进行密码认证等处理
     * 现在我们不要shirofiter进行密码的认证了，所以这个作用被弱化
    */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        StatelessToken token = (StatelessToken) authenticationToken;
        String username = token.getUsername();
        Response<AdminUserDto> userRes =  adminUserService.findUserByUsername(username);
        if (userRes.getCode() != HttpBizCode.SUCCESS.getCode()) {
            return null;
        }
        AdminUserEntity userEntity = BeanHelper.convertBean(userRes.getResult(), AdminUserEntity::new);
        return new SimpleAuthenticationInfo(userEntity,userEntity.getPassword(),username);
    }

}
