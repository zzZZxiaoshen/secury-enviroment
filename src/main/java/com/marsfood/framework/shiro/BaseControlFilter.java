package com.marsfood.framework.shiro;

import com.alibaba.fastjson.JSONObject;
import com.marsfood.entity.user.AdminUserEntity;
import com.marsfood.utils.TokenUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * 权限控制器父类
 * @author huangxingguang
 * @date 2018/11/01
 */
public abstract class BaseControlFilter extends AccessControlFilter {

    /**
     * 无权限返回错误消息
     */
    boolean notAuth(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ((HttpServletResponse) response).setHeader("Content-type", "application/json;charset=UTF-8");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 403);
        jsonObject.put("message", "登录凭证错误或已失效");
        httpResponse.getWriter().write(jsonObject.toJSONString());
        return false;
    }

    /**
     * 无权限跳转至登录页
     */
    boolean notAuth(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/view/login").forward(request, response);
        return false;
    }

    /**
     * 验证是否有权限
     */
    boolean auth(HttpServletRequest request, HttpServletResponse response, String token) {
        if (StringUtils.isBlank(token)) {
            return false;
        }
        if (!TokenUtils.verify(token)) {
            return false;
        }
        AdminUserEntity user = TokenUtils.getInfo(token, AdminUserEntity.class);
        if (user == null || StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            return false;
        }
        try {
            getSubject(request, response).login(new StatelessToken(user.getUsername(), user.getPassword()));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    private static final Pattern PATTERN = Pattern.compile("^/view/.*$");

    /**
    *  判断有无权限的具体逻辑再这里实现。 也可以使用默认的shirofilter进行处理简单的页面跳转等逻辑
    */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = TokenUtils.request2Token(request);
        // 验证用户是否有权限
        if (auth(request, response, token)) {
            return true;
        }
        if (PATTERN.matcher(request.getServletPath()).matches()) {
            return notAuth(request, response);
        }
        return notAuth(response);
    }

//    @Override
    protected boolean onAccessDenied1(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = TokenUtils.request2Token(request);
        if (auth(request, response, token)) {
            // 通过校验表示已经登陆过了，直接跳转首页
            response.sendRedirect(request.getContextPath() + "/view/index");
            return false;
        }
        return true;
    }

}
