package com.marsfood.framework.shiro;

import com.alibaba.fastjson.JSONObject;
import com.marsfood.entity.user.AdminUserEntity;
import com.marsfood.utils.TokenUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

/**
 * 权限控制器
 * @author shenkai
 * @date 2018/12/29
 */
public class BaseControllerFilter extends AccessControlFilter {


    /**
    * 判断用户有没有权限 即 认证在过滤器处理了 原因是解决前后端分离
    */
    public boolean auth(HttpServletRequest request ,HttpServletResponse response ,String token){
        if (token == null) {
            return false;
        }
        if (!TokenUtils.verify(token)) {
            return false;
        }
        AdminUserEntity user = TokenUtils.getInfo(token, AdminUserEntity.class);
        if (user != null) {
            try {
                getSubject(request, response).login(new StatelessToken(user.getUsername(), user.getPassword()));
                return true;
            } catch (AuthenticationException e) {
                return false;
            }
        }
        return false;
    }



   /**
   * 访问接口没有被认证处理
   */
   protected boolean noauth(ServletResponse response) throws IOException {
        ((HttpServletResponse) response).setHeader("Content-type", "application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "203");
        jsonObject.put("msg", "用户未登入或者token已经失效");
        writer.print(jsonObject.toJSONString());
        return false;
    }

    /**
    * 访问页面没有被处理
    */
    protected boolean notauth(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/login").forward(request, response);
        return false;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return false;
    }

}
