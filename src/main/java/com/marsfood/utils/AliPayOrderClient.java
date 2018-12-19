package com.marsfood.utils;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.Map;

public class AliPayOrderClient {
    private static final Logger LOGGER = LogManager.getLogger(AliPayOrderClient.class);
    private static final FastDateFormat FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

    private String format;

    private String charset;

    private String signType;

    private String appId;

    private String notifyURL;

    private String serverURL;

    private String publicKey;

    private String privateKey;

    private String subject;

    public String acquireTradeBody(String orderNo, String price) {
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
        request.setNotifyUrl(notifyURL);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("out_trade_no", orderNo);
        jsonObject.put("total_amount", price);
        jsonObject.put("subject", subject);
        jsonObject.put("time_expire", FORMAT.format(DateUtils.addMinutes(new Date(), 2)));
        request.setBizContent(jsonObject.toJSONString());
        AlipayClient alipayClient = new DefaultAlipayClient(serverURL, appId, privateKey, format, charset, publicKey, signType);
        try {
            return alipayClient.pageExecute(request).getBody();
        } catch (AlipayApiException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public boolean checkSign(final Map<String, String> requestParams) {
        try {
            return AlipaySignature.rsaCheckV1(requestParams, publicKey, "UTF-8", signType);
        } catch (AlipayApiException e) {
            return false;
        }
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getNotifyURL() {
        return notifyURL;
    }

    public void setNotifyURL(String notifyURL) {
        this.notifyURL = notifyURL;
    }

    public String getServerURL() {
        return serverURL;
    }

    public void setServerURL(String serverURL) {
        this.serverURL = serverURL;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
