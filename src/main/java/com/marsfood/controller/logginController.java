package com.marsfood.controller;

import com.marsfood.entity.response.HttpBizCode;
import com.marsfood.entity.response.ResponseEntity;
import com.marsfood.utils.AppConfigUtils;
import com.marsfood.utils.EncryptUtils;
import com.marsfood.utils.TokenUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 登入控制器
 *
 * @author shenkai
 * @date 2018/12/28
 */
@Controller
public class logginController {

    @ResponseBody
    @RequestMapping("/mylogin")
    public ResponseEntity loggin(String name, String password) {
        ResponseEntity response = ResponseEntity.newInstance();
        if (StringUtils.isBlank(name) || StringUtils.isBlank(password)) {
            return response.fill(HttpBizCode.ILLEGAL, "请输入用户名和密码", StringUtils.EMPTY);
        }
        if (StringUtils.isBlank(password)|| !StringUtils.equals(AppConfigUtils.PASSWORD, DigestUtils.md5Hex(password))) {
            return response.fill(HttpBizCode.ILLEGAL, "请输入正确的密码", StringUtils.EMPTY);
        }
        String token = TokenUtils.sign(name);
        return response.fill(HttpBizCode.SUCCESS, HttpBizCode.SUCCESS.getMessage(), token);
    }

    /**
     * 用于校验token过期时间
     */
    public String buidToken(String string) {
        return string + "|" + DateUtils.addMinutes(new Date(), 30).getTime()+"|";
    }

}
