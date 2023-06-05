package com.iss.ua.lark.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.iss.ua.lark.system.controller.BaseController;
import com.iss.ua.lark.common.core.domain.AjaxResult;
import com.iss.ua.lark.system.domain.model.RegisterBody;
import com.iss.ua.lark.common.utils.StringUtils;
import com.iss.ua.lark.framework.web.service.SysRegisterService;
import com.iss.ua.lark.system.service.ISysConfigService;

/**
 * 注册验证
 * 
 * @author lark
 */
@RestController
public class SysRegisterController extends BaseController
{
    @Autowired
    private SysRegisterService registerService;

    @Autowired
    private ISysConfigService configService;

    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterBody user)
    {
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser"))))
        {
            return error("当前系统没有开启注册功能！");
        }
        String msg = registerService.register(user);
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }
}
