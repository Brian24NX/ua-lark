package com.iss.ua.lark.common.exception.user;

import com.iss.ua.lark.common.exception.base.BaseException;

/**
 * 用户信息异常类
 * 
 * @author lark
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
