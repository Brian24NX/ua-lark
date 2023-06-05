package com.iss.ua.lark.controller.tool;

import com.iss.ua.lark.common.core.domain.AjaxResult;
import com.iss.ua.lark.common.utils.MessageUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("健康检查")
@RestController
public class HealthController {

    @GetMapping("/health")
    public AjaxResult health(){
        return AjaxResult.success(MessageUtils.message("demo.health"));
    }
}
