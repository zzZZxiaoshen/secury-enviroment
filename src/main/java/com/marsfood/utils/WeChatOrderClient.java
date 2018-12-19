package com.marsfood.utils;

import com.alibaba.fastjson.JSON;
import com.marsfood.dto.PaymentOrderDto;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * 小程序用户Id工具
 * @author zhuhongxin
 * @date 2018/12/04
 */
public class WeChatOrderClient {

    private static final FastDateFormat FORMAT = FastDateFormat.getInstance("yyyyMMddHHmmss");

    private String appId;

    private String payKey;

    private String mchId;

    private String notifyURL;

    private String grantType;

    private String secret;

    private String tokenServer;

    private String orderServer;


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPayKey() {
        return payKey;
    }

    public void setPayKey(String payKey) {
        this.payKey = payKey;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getNotifyURL() {
        return notifyURL;
    }

    public void setNotifyURL(String notifyURL) {
        this.notifyURL = notifyURL;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getTokenServer() {
        return tokenServer;
    }

    public void setTokenServer(String tokenServer) {
        this.tokenServer = tokenServer;
    }

    public String getOrderServer() {
        return orderServer;
    }

    public void setOrderServer(String orderServer) {
        this.orderServer = orderServer;
    }

}
