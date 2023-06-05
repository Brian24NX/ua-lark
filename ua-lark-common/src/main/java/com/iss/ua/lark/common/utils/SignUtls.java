package com.iss.ua.lark.common.utils;


import cn.hutool.crypto.digest.DigestUtil;

public class SignUtls {
    public static String getSign(String appId,String secretKey,String timestamp){
        String md5Key = timestamp + secretKey + appId;
        return DigestUtil.sha256Hex(md5Key);
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println(SignUtls.getSign("8599081689","MzG3h0J3M039zF7x215Uwx7X","1667958202000"));
    }
}
