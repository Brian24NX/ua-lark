package com.iss.ua.lark.system.service;

import me.chanjar.weixin.cp.bean.WxCpUser;

/**
 * @author iSoftStone-Robert
 * @date 2023/3/16
 */
public interface IWecomService {

    /**
     * 获取扫码授权用户的详细信息
     *
     * @param authCode 用户授权码
     * @return 授权用户的详细信息
     */
    WxCpUser getAuthUserInfo(String authCode);

}
