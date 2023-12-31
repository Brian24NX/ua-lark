package com.iss.ua.lark.controller;

/**
 * @author Hanson
 * @date 2021/11/22  22:09
 */


import cn.hutool.jwt.JWTUtil;
import com.alibaba.fastjson.JSONObject;
import com.hanson.mybatis.domain.PageQuery;
import com.hanson.rest.PageResult;
import com.hanson.rest.SimpleResult;
import com.iss.ua.lark.common.bo.UserInfoBo;
import com.iss.ua.lark.common.constant.Constant;
import com.iss.ua.lark.common.util.JwtUtil;
import com.iss.ua.lark.common.util.LarkUtil;
import com.iss.ua.lark.serevice.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import java.util.List;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 示例控制器
 * @author hanson
 */
@Slf4j
@Api(value = "用户相关接口")
@RestController
public class UserInfoController {
	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	LarkUtil larkUtil;

	@Autowired
	JwtUtil jwtUtil;

	@GetMapping("/user-info/detail/{id}")
	@ApiOperation(value = "接口名称:用户详情", notes = "接口描述:用户详情")
	@ApiImplicitParams({@ApiImplicitParam(name = Constant.TOKEN, paramType = "header" ,required = false)})
	public SimpleResult<UserInfoBo> get(@PathVariable("id") Long id) {
		UserInfoBo userInfoBo = userInfoService.get(id);
		return SimpleResult.success(userInfoBo);
	}

	@PostMapping("/user-info/list")
	public SimpleResult<List<UserInfoBo>> list(@RequestBody UserInfoBo userInfoBo) {
		List<UserInfoBo> ret = userInfoService.list(userInfoBo);
		return SimpleResult.success(ret);
	}

	@PostMapping("/user-info/page")
	public SimpleResult<PageResult<UserInfoBo>> page(@RequestBody PageQuery<UserInfoBo> pageQuery) {
		PageResult<UserInfoBo> page = userInfoService.page(pageQuery);
		return SimpleResult.success(page);
	}


	/**
	 * 枚举传入 name
	 * 如果传入主键则是更新，否则是新增。
	 */
	@PostMapping("/user-info/save")
	public SimpleResult<UserInfoBo> save(@Valid @RequestBody UserInfoBo userInfoBo) {
		UserInfoBo result = userInfoService.save(userInfoBo);
		return SimpleResult.success(result);
	}

	@DeleteMapping("/user-info/del/{id}")
	public SimpleResult<Integer> del(@PathVariable("id") Long id) {
		return SimpleResult.success(userInfoService.del(id));
	}




	@GetMapping("/login")
	public SimpleResult<String> login(@RequestParam String code) {
		log.info("go in login");
		//获取页面传来的员工userId
		log.info("进来了");
		JSONObject result = larkUtil.getUser(code);
		if (result != null) {
			String token = jwtUtil.createToken(result.getString("employee_id"));
			return SimpleResult.success(token);
		}
		return null;
	}







}
