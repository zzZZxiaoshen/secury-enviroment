package com.marsfood.framework.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.marsfood.controller.BaseController;
import com.marsfood.entity.response.HttpBizCode;
import com.marsfood.utils.AppConfigUtils;
import com.marsfood.utils.EncryptUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Locale;

/**
 * 权限拦截器
 * @author huangxingguang
 * @date 2018/12/13
 */
public class AuthorizationInterceptor implements HandlerInterceptor {

    private static final String TOKEN_KEY = "token";
    private final static FastDateFormat FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Object handlerBean = handlerMethod.getBean();
        if (!BaseController.class.isAssignableFrom(handlerBean.getClass())) {
            return true;
        }
//        String token = request.getHeader(TOKEN_KEY);
        String token = request.getParameter(TOKEN_KEY);

        if (StringUtils.isBlank(token)) {
            this.outputMessage(response, HttpBizCode.NOT_LOGIN.getCode(), "请先登录");
            return false;
        }
        String decrypt = EncryptUtils.getInstance(AppConfigUtils.SECRET_KEY).decrypt(token);
        if (StringUtils.isBlank(decrypt)) {
            this.outputMessage(response, HttpBizCode.NOT_LOGIN.getCode(), "请先登录");
            return false;
        }
        String[] tokenMessage = decrypt.split("\\|");
        final int messageLength = 2;
        if (tokenMessage.length < messageLength) {
            this.outputMessage(response, HttpBizCode.NOT_LOGIN.getCode(), "请先登录");
            return false;
        }
        String username = tokenMessage[0];
        if (StringUtils.isBlank(username) || !StringUtils.equals(AppConfigUtils.USERNAME, username)) {
            this.outputMessage(response, HttpBizCode.NOT_LOGIN.getCode(), "请先登录");
            return false;
        }
        Long expireTime = Long.valueOf(tokenMessage[1]);
        // TOKEN过期
        if (FORMAT.parse(FORMAT.format(expireTime)).before(new Date())) {
            this.outputMessage(response, HttpBizCode.EXPIRED.getCode(), "登录已过期");
            return false;
        }
        return true;
    }

    private void outputMessage(HttpServletResponse response, int code, String message) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("data", StringUtils.EMPTY);
        jsonObject.put("message", message);
        writer.println(jsonObject.toJSONString());
        writer.flush();
        writer.close();
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) {

    }

}
