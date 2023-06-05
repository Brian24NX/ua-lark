package com.iss.ua.lark.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.iss.ua.lark.common.config.wxcp.WxCpConfiguration;
import com.iss.ua.lark.common.exception.ServiceException;
import com.iss.ua.lark.system.service.IWecomService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpOauth2UserInfo;
import me.chanjar.weixin.cp.bean.WxCpUser;
import org.springframework.stereotype.Service;

/**
 * @author iSoftStone-Robert
 * @date 2023/3/16
 */
@Service
@Slf4j
public class WecomServiceImpl implements IWecomService {

    /**
     * 获取扫码授权用户的详细信息
     *
     * @param authCode 用户授权码
     * @return 授权用户的详细信息
     */
    public WxCpUser getAuthUserInfo(String authCode) {
        final WxCpService wxCpService = WxCpConfiguration.getCpService(1000016);
        try {
            log.info("扫码授权登录，授权码：[{}]", authCode);
            WxCpOauth2UserInfo userInfo = wxCpService.getOauth2Service().getUserInfo(authCode);
            log.info("获取授权用户身份信息：[{}]", JSONObject.toJSONString(userInfo));
            WxCpUser wxCpUser = wxCpService.getUserService().getById(userInfo.getUserId());
            log.info("获取授权用户详细信息：[{}]", JSONObject.toJSONString(wxCpUser));
            return wxCpUser;
        } catch (WxErrorException e) {
            log.error("企业微信客户端调用错误:", e);
            throw new ServiceException("企业微信客户端访问失败");
        }
    }

}
