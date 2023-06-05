package com.iss.ua.lark.common.util;

import com.alibaba.fastjson.JSONObject;
import com.iss.ua.lark.common.constant.LarkConstants;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;


@Log4j2
@Component
public class LarkUtil {

    @Value("${lark.appId}")
    private String appId;

    @Value("${lark.appSecret}")
    private String appSecret;

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    /**
     * 常规token
     * 获取企业微信accessToken
     * @return
     */
    public  String getAccessToken(){

        JSONObject param= new JSONObject();
        param.put("app_id",appId);
        param.put("app_secret",appSecret);
        JSONObject result = restTemplate.postForObject(LarkConstants.app_access_token_url,param,JSONObject.class);
        log.info("result:"+result);
        return result.getString("app_access_token");
    }

    /**
     * 常规token
     * 获取企业微信accessToken
     * @return
     */
    public  JSONObject getUser(String code){
        JSONObject param= new JSONObject();
        param.put("code",code);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add("Authorization", "Bearer " + getAccessToken());
        HttpEntity<Object> entity = new HttpEntity<>(param, headers);
        JSONObject result = restTemplate.postForObject(LarkConstants.user_url, entity, JSONObject.class);
        log.info("code:{} header:{}",code,headers);
        log.info("result:"+result);
        return result.getJSONObject("data");
    }

}
