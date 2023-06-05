package com.iss.ua.lark.framework.web.service;

import javax.annotation.Resource;

import com.iss.ua.lark.framework.manager.AsyncManager;
import com.iss.ua.lark.framework.manager.factory.AsyncFactory;
import com.iss.ua.lark.framework.security.context.AuthenticationContextHolder;
import com.iss.ua.lark.common.utils.DateUtils;
import com.iss.ua.lark.common.utils.MessageUtils;
import com.iss.ua.lark.common.utils.ServletUtils;
import com.iss.ua.lark.common.utils.StringUtils;
import com.iss.ua.lark.system.service.IWecomService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.cp.bean.WxCpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.iss.ua.lark.common.constant.CacheConstants;
import com.iss.ua.lark.common.constant.Constants;
import com.iss.ua.lark.common.core.domain.entity.SysUser;
import com.iss.ua.lark.system.domain.model.LoginUser;
import com.iss.ua.lark.common.core.redis.RedisCache;
import com.iss.ua.lark.common.exception.ServiceException;
import com.iss.ua.lark.common.exception.user.CaptchaException;
import com.iss.ua.lark.common.exception.user.CaptchaExpireException;
import com.iss.ua.lark.common.exception.user.UserPasswordNotMatchException;
import com.iss.ua.lark.common.utils.ip.IpUtils;
import com.iss.ua.lark.system.service.ISysConfigService;
import com.iss.ua.lark.system.service.ISysUserService;

/**
 * 登录校验方法
 * 
 * @author lark
 */
@Component
@Slf4j
public class SysLoginService
{
    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;
    
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysConfigService configService;
    @Autowired
    private IWecomService wecomService;
    @Autowired
    private SysPermissionService permissionService;

    /**
     * 登录验证
     * 
     * @param username 用户名
     * @param password 密码
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid)
    {
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        // 验证码开关
        if (captchaEnabled)
        {
            validateCaptcha(username, code, uuid);
        }
        // 用户验证
        Authentication authentication = null;
        try
        {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            AuthenticationContextHolder.setContext(authenticationToken);
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);
        }
        catch (Exception e)
        {
            if (e instanceof BadCredentialsException)
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new ServiceException(e.getMessage());
            }
        }
        finally
        {
            AuthenticationContextHolder.clearContext();
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        recordLoginInfo(loginUser.getUserId());
        // 生成token
        return tokenService.createToken(loginUser);
    }

    /**
     * 企微用户扫码登录
     *
     * @param wecomUserCode 企微用户授权码
     * @return 结果
     */
    public String wecomLogin(String wecomUserCode) {
        // 获取该授权用户的信息
        WxCpUser wxCpUser = wecomService.getAuthUserInfo(wecomUserCode);

        // 查询该企业微信用户是否在系统用户表，否则创建新的系统用户账号
        SysUser sysUser = userService.selectUserByEmployeeNumber(wxCpUser.getUserId());
        if (sysUser == null) {
            // 创建新企业微信用户的系统用户账号
            sysUser = userService.createWecomSysUser(wxCpUser);
        }
        LoginUser loginUser = new LoginUser(
                sysUser.getUserId(),
                sysUser.getDeptId(),
                sysUser,
                permissionService.getMenuPermission(sysUser));
        loginUser.setEmployeeNumber(sysUser.getEmployeeNumber());
        String username = sysUser.getUserName();

        // 记录登录信息
        AsyncManager
                .me()
                .execute(
                        AsyncFactory.
                                recordLogininfor(
                                        username,
                                        Constants.LOGIN_SUCCESS,
                                        MessageUtils.message("user.login.success")
                                )
                );
        recordLoginInfo(sysUser.getUserId());
        // 生成token
        return tokenService.createToken(loginUser);
    }

    /**
     * 校验验证码
     * 
     * @param username 用户名
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid)
    {
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
            throw new CaptchaException();
        }
    }

    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(Long userId)
    {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setLoginIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        sysUser.setLoginDate(DateUtils.getNowDate());
        userService.updateUserProfile(sysUser);
    }
}
