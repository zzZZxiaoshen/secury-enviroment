package com.marsfood.framework.apectJ;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilterable;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.marsfood.dto.response.Response;
import com.marsfood.entity.response.HttpBizCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * 日志的后置通知切面类
 * @author shenkai
 * @date 2018/12/29
 */
public class LoggerLogspectJ implements AfterReturningAdvice {

    private static final Logger LOGGER = LogManager.getLogger(LoggerLogspectJ.class);
    private static final Pattern PATTERN = Pattern.compile("(((.*insert)|(.*delete)|(.*update)).*)");

    /**
    * 后置通知
    */
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        if (!PATTERN.matcher(method.getName()).matches()) {
            return;
        }
        if (!(returnValue instanceof Response)) {
            return;
        }
        Response response = (Response) returnValue;
        String methodName = target.getClass() + method.getName();
        String params =  JSON.toJSONString(methodName,SerializerFeature.WriteNullStringAsEmpty);
        if (((Response) returnValue).getResult()==null||((Response) returnValue).getCode()!= HttpBizCode.SUCCESS.getCode()) {
            LOGGER.error("[{}] result is not success. params:[{}]. message:[{}]", methodName, params, response.getMessage());
        }
    }

}
