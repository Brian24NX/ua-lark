package com.iss.ua.lark.controller;

import static com.hanson.rest.enmus.ErrorCodeEnum.INVALID_PARAMETER;

import com.hanson.rest.SimpleResult;
import com.iss.ua.lark.common.bo.UserInfoBo;
import com.iss.ua.lark.common.constant.Constant;
import com.iss.ua.lark.common.util.I18nMessageUtils;
import com.iss.ua.lark.common.util.UserThreadLocalUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 示例控制器
 * @author hanson
 */
@Api(value = "示例控制器")
@RestController
public class WelcomeController {
	@GetMapping("/api/v1/hello")
	@ApiOperation("hello world")
	public SimpleResult<String> hello() {
		UserInfoBo currentUser = UserThreadLocalUtil.getCurrentUser();
		return SimpleResult.success("hello world"+currentUser.getName());
	}

	@GetMapping("/api/v1/i18n")
	@ApiOperation("国际化")
	public SimpleResult<List<String>> getI18n() {
		List<String> list = new ArrayList<>();
		list.add(I18nMessageUtils.message("com.hanson.user.frozen"));
		list.add(I18nMessageUtils.message("com.hanson.user.notfound","hanson"));
		list.add(I18nMessageUtils.message("com.hanson.user.name.not.blank"));
		return SimpleResult.success(list);
	}
}
