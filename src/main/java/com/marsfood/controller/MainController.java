package com.marsfood.controller;

import com.marsfood.entity.response.HttpBizCode;
import com.marsfood.entity.response.ResponseEntity;
import com.marsfood.utils.AppConfigUtils;
import com.marsfood.utils.EncryptUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Date;

/**
 * 用户登录控制器
 * @author zhuhongxin
 * @date 2018/12/13
 */
@Controller
public class MainController {
    /**
     * 进入登录页
     */
    @RequestMapping("/view/login")
    public String viewLogin() {
        return "/login";
    }

    /**
     * 进入错误页面
     */
    @RequestMapping("/view/error")
    public String viewError() {
        return "/error";
    }

    /**
     * 用户登录
     */
    @ResponseBody
    @RequestMapping("/user/login")
    public ResponseEntity login(String username, String password) {
        ResponseEntity response = ResponseEntity.newInstance();
        if (StringUtils.isBlank(username) || !StringUtils.equals(AppConfigUtils.USERNAME, username)) {
            return response.fill(HttpBizCode.ACCOUNT_NOT_EXIST, HttpBizCode.ACCOUNT_NOT_EXIST.getMessage(), null);
        }
        if (StringUtils.isBlank(password) || !StringUtils.equals(AppConfigUtils.PASSWORD, DigestUtils.md5Hex(password))) {
            return response.fill(HttpBizCode.PWD_ERROR, HttpBizCode.PWD_ERROR.getMessage(), null);
        }
        String token = EncryptUtils.getInstance(AppConfigUtils.SECRET_KEY).encrypt(this.buildToken(username));
        return response.fill(HttpBizCode.SUCCESS, HttpBizCode.SUCCESS.getMessage(), token);
    }

    private String buildToken(String token) {
        return token + "|" + DateUtils.addDays(new Date(), 30).getTime();
    }

    public static void main(String[] args) {
        System.out.println(DigestUtils.md5Hex("123456"));
    }

}
