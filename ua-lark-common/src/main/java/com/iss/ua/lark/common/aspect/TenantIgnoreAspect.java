package com.iss.ua.lark.common.aspect;

import com.iss.ua.lark.common.annotation.IgnoreTenant;
import com.iss.ua.lark.common.util.MybatisTenantContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author: HansonHu
 * @date: 2023-06-09 19:06
 **/
@Aspect
@Slf4j
@Component
public class TenantIgnoreAspect {
    /**
     * 切入点
     */
    @Pointcut("@within(com.iss.ua.lark.common.annotation.IgnoreTenant) ||@annotation(com.iss.ua.lark.common.annotation.IgnoreTenant)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        try {
            Class<?> targetClass = point.getTarget().getClass();
            IgnoreTenant classIgnoreTenant = targetClass.getAnnotation(IgnoreTenant.class);
            MethodSignature signature = (MethodSignature) point.getSignature();
            Method method = signature.getMethod();
            IgnoreTenant methodIgnoreTenant = method.getAnnotation(IgnoreTenant.class);

            //判断类上是否有注解
            boolean isClassAnnotated = AnnotationUtils.isAnnotationDeclaredLocally(IgnoreTenant.class, targetClass);
            //判断方法上是否有注解
            boolean isMethodAnnotated = Objects.nonNull(methodIgnoreTenant);

            //如果类上有
            if (isClassAnnotated) {
                MybatisTenantContext.set(classIgnoreTenant.isIgnore());
            }
            //如果方法上有 以方法上的为主
            if (isMethodAnnotated) {
                MybatisTenantContext.set(methodIgnoreTenant.isIgnore());
            }
            Object result = point.proceed();
            return result;
        } finally {
            MybatisTenantContext.clear();
        }
    }
}
