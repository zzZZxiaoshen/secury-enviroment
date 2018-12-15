package com.marsfood.framework.exception;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用异常处理
 * @author zhuhongxin
 * @date 2018/09/25
 */
public class CommonSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {

    private static final Logger LOGGER = LogManager.getLogger(CommonSimpleMappingExceptionResolver.class.getName());

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        LOGGER.error(e.getMessage());
        for (int i = 0; i < e.getStackTrace().length; i++) {
            LOGGER.error(e.getStackTrace()[i].toString());
        }
        String viewName = determineViewName(e, request);
        if (StringUtils.isBlank(viewName)) {
            return this.outnMessage();
        }
        if (ContentType.APPLICATION_JSON.getMimeType().equals(request.getContentType())) {
            return this.outnMessage();
        } else {
            Integer statusCode = determineStatusCode(request, viewName);
            if (statusCode != null) {
                applyStatusCodeIfPossible(request, response, statusCode);
            }
            return getModelAndView(viewName, e, request);
        }

    }

    private ModelAndView outnMessage() {
        ModelAndView modelAndView = new ModelAndView();
        FastJsonJsonView jsonView = new FastJsonJsonView();
        Map<String, Object> attributes = new HashMap<>(2);
        attributes.put("code", 500);
        attributes.put("msg", "系统繁忙，请稍后再试");
        jsonView.setAttributesMap(attributes);
        modelAndView.setView(jsonView);
        return modelAndView;
    }

}
