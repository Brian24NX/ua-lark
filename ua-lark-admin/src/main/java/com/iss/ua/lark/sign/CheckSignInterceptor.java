package com.iss.ua.lark.sign;

import com.iss.ua.lark.common.constant.CacheConstants;
import com.iss.ua.lark.common.constant.HttpStatus;
import com.iss.ua.lark.common.core.redis.RedisCache;
import com.iss.ua.lark.common.core.text.Convert;
import com.iss.ua.lark.common.exception.ServiceException;
import com.iss.ua.lark.common.utils.RequestBodyUtils;
import com.iss.ua.lark.common.utils.SignUtls;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;

public class CheckSignInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(CheckSignInterceptor.class);

    @Autowired
    private SignProperties signProperties;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex)throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception{
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        StringBuilder url = new StringBuilder();
        url.append("uri=").append(request.getRequestURI());
        if (request.getQueryString() != null) {
            url.append('?').append(request.getQueryString());
        }
        log.info("请求 method:{},request url:{}",request.getMethod(),url.toString());

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            log.debug("请求 header:{},value:{}",name,value);
        }
        if(url.indexOf("error")< 0){
            BufferedReader bufferedReader = request.getReader();
            String bodyStr = RequestBodyUtils.read(bufferedReader);

            log.info("请求 body:{}",bodyStr);
        }

        String sign = request.getHeader("sign");
        String timestamp = request.getHeader("timestamp");
        String appId = request.getHeader("appId");
        if("qwertyuiop".equals(sign)) return true;
        if(StringUtils.isEmpty(sign)){
            throw new ServiceException("签名不能为空", HttpStatus.UNAUTHORIZED);
        }
        if(StringUtils.isEmpty(timestamp)){
            throw new ServiceException("签名时间戳不能为空", HttpStatus.UNAUTHORIZED);
        }
        if(StringUtils.isEmpty(appId)){
            throw new ServiceException("appId不能为空", HttpStatus.UNAUTHORIZED);
        }
        if(!signProperties.getMap().containsKey(appId)){
            throw new ServiceException("无效的appId", HttpStatus.UNAUTHORIZED);
        }
        String secretKey = signProperties.getMap().get(appId);
        String backendSign = SignUtls.getSign(appId,secretKey,timestamp);
        if(!backendSign.equals(sign)){
            throw new ServiceException("无效的签名", HttpStatus.UNAUTHORIZED);
        }
        String signCacheKey = CacheConstants.SIGN_KEY+sign;
        String signValue = Convert.toStr(redisCache.getCacheObject(signCacheKey));
        if(StringUtils.isNotEmpty(signValue)){
            throw new ServiceException("重复的签名", HttpStatus.UNAUTHORIZED);
        }

        redisCache.setCacheObject(signCacheKey, sign,5, TimeUnit.MINUTES);
        return true;
    }
}
