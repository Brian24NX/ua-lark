package com.iss.ua.lark.common.enums.error;

import com.hanson.rest.enmus.ErrorCode;
import lombok.Getter;

/**
 * @author Hanson
 * @date 2022/1/11  16:12
 */
@Getter
public enum UserErrorCodeEnum implements ErrorCode {
    FROZEN_USER("000101", "com.hanson.user.frozen"),
    ;

    private String code;
    private String message;

    private UserErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
