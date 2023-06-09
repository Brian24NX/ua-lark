package com.iss.ua.lark.configuration;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.hanson.mybatis.injector.MySqlInjector;
import com.iss.ua.lark.common.bo.UserInfoBo;
import com.iss.ua.lark.common.util.UserThreadLocalUtil;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;

/**
 * @author: HansonHu
 * @date: 2023-06-09 00:11
 **/

@Configuration
public class MybatisPlusTenantConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //分页插件
        interceptor.addInnerInterceptor((new PaginationInnerInterceptor(DbType.MYSQL)));
        //租户插件
        interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new TenantLineHandler() {
            @Override
            public Expression getTenantId() {
                UserInfoBo currentUser = UserThreadLocalUtil.getCurrentUser();
                //获得当前登录用户的租户id
                return new LongValue(1L);
            }
        }));
        return interceptor;
    }

    @Bean
    public ISqlInjector sqlInjector() {
        return new MySqlInjector();
    }
}
