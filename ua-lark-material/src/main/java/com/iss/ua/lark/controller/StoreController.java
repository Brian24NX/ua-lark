package com.iss.ua.lark.controller;

/**
 * @author Hanson
 * @date 2021/11/22  22:09
 */

import com.hanson.mybatis.domain.PageQuery;
import com.hanson.rest.PageResult;
import com.hanson.rest.SimpleResult;
import com.iss.ua.lark.common.bo.UserInfoBo;
import com.iss.ua.lark.common.constant.Constant;
import com.iss.ua.lark.serevice.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 店铺相关接口
 * @author hanson
 */
@Api(value = "店铺相关接口")
@RestController
public class StoreController {
	@Autowired
	private UserInfoService userInfoService;

	@GetMapping("/store/detail/{id}")
	@ApiOperation(value = "接口名称:店铺详情", notes = "接口描述:店铺详情")
	@ApiImplicitParams({@ApiImplicitParam(name = Constant.TOKEN, paramType = "header" ,required = false)})
	public SimpleResult<UserInfoBo> get(@PathVariable("id") Long id) {
		UserInfoBo userInfoBo = userInfoService.get(id);
		return SimpleResult.success(userInfoBo);
	}

	@PostMapping("/store/list")
	public SimpleResult<List<UserInfoBo>> list(@RequestBody UserInfoBo userInfoBo) {
		List<UserInfoBo> ret = userInfoService.list(userInfoBo);
		return SimpleResult.success(ret);
	}

	@PostMapping("/store/page")
	public SimpleResult<PageResult<UserInfoBo>> page(@RequestBody PageQuery<UserInfoBo> pageQuery) {
		PageResult<UserInfoBo> page = userInfoService.page(pageQuery);
		return SimpleResult.success(page);
	}


	/**
	 * 枚举传入 name
	 * 如果传入主键则是更新，否则是新增。
	 */
	@PostMapping("/store/save")
	public SimpleResult<UserInfoBo> save(@Valid @RequestBody UserInfoBo userInfoBo) {
		UserInfoBo result = userInfoService.save(userInfoBo);
		return SimpleResult.success(result);
	}

	@DeleteMapping("/store/del/{id}")
	public SimpleResult<Integer> del(@PathVariable("id") Long id) {
		return SimpleResult.success(userInfoService.del(id));
	}
}
