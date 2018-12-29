package com.marsfood.framework.shiro;

import com.marsfood.utils.TokenUtils;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限控制器
 * @author shenkai
 * @date 2018/12/29
 */
public class BaseControllerFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    /**
    * 判断有无权限之后进行自定义的业务逻辑处理，也可以使用默认的框架封装的shirofilter。
     *                                          进行简单业务逻辑处理。
    */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        String token = TokenUtils.request2Token(servletRequest);
        if (token == null) {
            return false;
        }
        if (!TokenUtils.verify(token)) {
            return false;
        }

        return false;
    }
}
