package com.example.mybatisstudy.aop;

import java.util.Objects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import com.example.mybatisstudy.datasouce.DynamicDataSourceContextHolder;

/**
 * @author ouyangchao <ouyangchao@kuaishou.com>
 * Created on 2022-01-06
 */
@Aspect
@Component
public class DynamicDataSourceAspect {

    @Pointcut("@within(com.example.mybatisstudy.aop.DS)")
    public void dataSourcePointCut() {

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String dsKey = getDSAnnotation(joinPoint).value();
        DynamicDataSourceContextHolder.setContextKey(dsKey);
        try {
            return joinPoint.proceed();
        } finally {
            DynamicDataSourceContextHolder.removeContextKey();
        }
    }

    /**
     * 根据类或方法获取数据源注解
     */
    private DS getDSAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
        // 这里注意需要用AnnotationUtils.findAnnotation方法去找到代理类对应的实际类
        DS dsAnnotation = AnnotationUtils.findAnnotation(joinPoint.getTarget().getClass(),DS.class);
        // 先判断类的注解，再判断方法注解
        if (Objects.nonNull(dsAnnotation)) {
            return dsAnnotation;
        } else {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            return methodSignature.getMethod().getAnnotation(DS.class);
        }
    }
}

