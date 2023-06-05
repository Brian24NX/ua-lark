package com.iss.ua.lark.sign;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkInterceptor()).excludePathPatterns("/", "/**/*.png",
                "/*.html", "/**/*.html", "/**/*.css", "/**/*.js",
                "/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/*/api-docs", "/druid/**");
    }

    @Bean
    public CheckSignInterceptor checkInterceptor() {
        return new CheckSignInterceptor();
    }
}
