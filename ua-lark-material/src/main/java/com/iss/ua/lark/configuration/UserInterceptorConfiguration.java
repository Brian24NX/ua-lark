package com.iss.ua.lark.configuration;

import com.iss.ua.lark.interceptor.StaffInfoInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: HansonHu
 * @date: 2022-09-15 10:03
 **/
@Configuration
public class UserInterceptorConfiguration implements WebMvcConfigurer {
    @Autowired
    private StaffInfoInterceptor staffInfoInterceptor;

    /**
     * 拦截器，将用户信息放入threadLocal
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 注册自定义的显示 ResponseResult 注解的拦截器
         */
        registry.addInterceptor(staffInfoInterceptor)
                // 拦截配置
                .addPathPatterns("/**")
                // 排除配置
                .excludePathPatterns("/error", "doc.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

        /** 配置knife4j 显示文档 */
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        /**
         * 配置swagger-ui显示文档
         */
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        /** 公共部分内容 */
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
