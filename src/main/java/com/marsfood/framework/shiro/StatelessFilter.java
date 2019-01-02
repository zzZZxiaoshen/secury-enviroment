package com.marsfood.framework.shiro;

import com.marsfood.utils.TokenUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;

/**
 * 身份认证过滤器
 * @author shenkai
 * @date 2019/1/2
 */
public class StatelessFilter  extends BaseControllerFilter{

    /**
    * 匹配登入页面的是否被认证的页面路径资源
    */
    private static final Pattern PATTERN = Pattern.compile("^/view/.*$");

    /**
     * 判断有无权限之后进行自定义的业务逻辑处理，也可以使用默认的框架封装的shirofilter。
     *                                          但是默认是基于session开发的框架。
     *再这个过滤中认证前后端分离的token有无，具体的token信息认证在shiro框架中处理，并且处理拦截与转发的资源。
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        String token = TokenUtils.request2Token(servletRequest);
        if (auth(servletRequest,servletResponse,token)) {
            return true;
        }
        // 页面没有认证有无token跳转登入页面
        if (PATTERN.matcher(servletRequest.getContextPath()).matches()) {
            return   super.notauth(request, response);
        }
        // 请求接口没有认证有无token响应消息
        return super.noauth(response);
    }

}
