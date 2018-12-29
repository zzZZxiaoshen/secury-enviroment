package com.marsfood.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * tooken工具类
 * @author shenkai
 * @date 2018/12/29
 */
public class TokenUtils {



    /**
     * 过期时间，15天内有效
     */
    private static final long EXPIRE_TIME = 60* 1000 ;

    /**
     * token内存放信息的key
     */
    public static final String TOKEN_INFO_KEY = "e92e0b";

    /**
     * token密钥
     */
    private static final String TOKEN_SECRET = "ec261428fda86c02d5dbb0a748e92e0b";

    /**
     * 生成token签名
     * @param obj 存入对象
     * @return TOKEN
     */
    public static String sign(Object obj) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            Map<String, Object> header = new HashMap<>(2);
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            return JWT.create().withHeader(header).withClaim(TOKEN_INFO_KEY, JSON.toJSONString(obj)).withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME)).sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 校验是否正确
     * @param token TOKEN
     * @return true：正确
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
    * 获取token内的信息
    * @param token
    * @return 用户信息
    */
    public static  <T>T getInfo(String token, Class<T> clazz){
        try {
            String value = getValueToken(token, TOKEN_INFO_KEY);
            if (StringUtils.isBlank(value)) {
                return null;
            }
            return JSONObject.parseObject(value, clazz);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
    * 从token中获取值
    * @param token token信息
    * @return token内的信息
    */
    public static String getValueToken(String token,String key) throws UnsupportedEncodingException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET))
                .build();
        DecodedJWT jwt = verifier.verify(token);
        Map<String, Claim> claims = jwt.getClaims();
        return claims.get(key).asString();
    }


    /**
     * 获取token
     */
    public static String request2Token(HttpServletRequest request) {
        if (request == null) {
            return org.apache.commons.lang.StringUtils.EMPTY;
        }
        // 首次就在header里面找
        String token = request.getHeader("token");
        if (org.apache.commons.lang.StringUtils.isNotEmpty(token)) {
            return token;
        }
        // 在参数找
        token = request.getParameter("token");
        if (org.apache.commons.lang.StringUtils.isNotEmpty(token)) {
            return token;
        }
        // 最后cookie找
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return org.apache.commons.lang.StringUtils.EMPTY;



    }


}
