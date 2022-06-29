package com.example.springboot.test.annotation;

import com.example.springboot.test.constant.DataSourceConstants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 标记注解可使用在方法与类上
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DS {
	// 默认值为MASTER
    String value() default DataSourceConstants.DS_KEY_MASTER;
}
