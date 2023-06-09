package com.iss.ua.lark.configuration;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.hanson.mybatis.injector.MySqlInjector;
import com.hanson.util.RequestUtils;
import com.iss.ua.lark.common.bo.UserInfoBo;
import com.iss.ua.lark.common.util.MybatisTenantContext;
import com.iss.ua.lark.common.util.UserThreadLocalUtil;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.StringValue;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.iss.ua.lark.common.constant.UserConstants.TENANT_KEY;

/**
 * @author: HansonHu
 * @date: 2023-06-09 00:11
 **/

@Configuration
public class MybatisPlusTenantConfig {
    @Value("com.hanson.tenant-ignore-table")
    private String tenantIgnoreTables;
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
                return new StringValue(currentUser.getTenantCode());
            }
            @Override
            public String getTenantIdColumn() {
                return TENANT_KEY;
            }
            @Override
            public boolean ignoreTable(String tableName) {
                //如果tenant为空则不进行过滤
                UserInfoBo currentUser = UserThreadLocalUtil.getCurrentUser();
                if(StringUtils.isBlank(currentUser.getTenantCode())){
                    return true;
                }
                //过滤表
                List<String> ignoreTable = Arrays.stream(tenantIgnoreTables.split(",")).map(String::trim).filter(org.springframework.util.StringUtils::hasLength).collect(Collectors.toList());
                for(int i = 0; i < ignoreTable.size(); ++i) {
                    String table = ignoreTable.get(i);
                    if (tableName.contains(table)) {
                        return true;
                    }
                }
                //是否有忽略注解 @IgnoreTenant
                if (Objects.nonNull(MybatisTenantContext.get())){
                    return MybatisTenantContext.get();
                }
                return false;
            }
        }));
        return interceptor;
    }

    @Bean
    public ISqlInjector sqlInjector() {
        return new MySqlInjector();
    }
}
