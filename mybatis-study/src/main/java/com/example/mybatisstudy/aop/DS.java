package com.example.mybatisstudy.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.example.mybatisstudy.constants.DataSourceConstants;

/**
 * @author ouyangchao <ouyangchao@kuaishou.com>
 * Created on 2022-01-06
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DS {
    /**
     * 数据源名称
     */
    String value() default DataSourceConstants.DS_KEY_MASTER;
}
