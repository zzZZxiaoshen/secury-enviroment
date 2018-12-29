package com.marsfood.framework.interceptor;


import com.alibaba.fastjson.JSONObject;
import com.marsfood.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

public class MyAuthorizationInterceptor implements HandlerInterceptor {

    public static final FastDateFormat FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

  /*
     方案一：
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 获取token
        String token = TokenUtils.request2Token(request);
        if (StringUtils.isBlank(token)) {
            printConse(response, 200,"请先登入");
            return false;
        }
        // 进行解密
        String decrypt = EncryptUtils.getInstance(AppConfigUtils.SECRET_KEY).decrypt(token);
        if (StringUtils.isBlank(decrypt)) {
            printConse(response, 200,"请先登入");
            return false;
        }
        String name = decrypt.substring(0, decrypt.indexOf("|"));
        // 进行验证
        if (!StringUtils.equals(name, AppConfigUtils.USERNAME)) {
            printConse(response, 200,"请先登入");
            return false;
        }
        String time = decrypt.substring( decrypt.indexOf("|")+1,decrypt.lastIndexOf("|"));
        // 过期判断
        Long expireDate  = Long.valueOf(time);
        if (FORMAT.parse(FORMAT.format(expireDate)).before(new Date())) {
            printConse(response, 200,"token过期");
            return false;
        }
        return true;
    }*/

    /**
    * 方案二
    */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取token
        String token = TokenUtils.request2Token(request);
        if (token == null) {
            this.printConse(response,200,"请先登入");
            return false;
        }
        // 进行解密
        boolean verify = TokenUtils.verify(token);
        if (!verify) {
            this.printConse(response,200,"token已经过期，请先登入");
            return false;
        }
        String valueToken = TokenUtils.getValueToken(token, TokenUtils.TOKEN_INFO_KEY);
        if (valueToken ==null) {
            this.printConse(response,200,"请先登入");
            return false;
        }
        return true;
    }

    private void printConse(HttpServletResponse response,Integer code, String mesg) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("data", StringUtils.EMPTY);
        jsonObject.put("mesg", mesg);
        writer.print(jsonObject);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }



}
