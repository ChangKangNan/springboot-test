package com.example.springboot.test.aspect;

import com.example.springboot.test.annotation.DS;
import com.example.springboot.test.constant.DataSourceConstants;
import com.example.springboot.test.datasource.DynamicDataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 切面
 */
@Aspect
@Component
@Order(1)
public class DynamicDataSourceAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("@annotation(com.example.springboot.test.annotation.DS)")
    public void dataSourcePointCut() {
    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        logger.info("method:" + signature.getMethod().getName());
        Class<?> aClass = Class.forName(signature.getDeclaringType().getName());
        logger.info("开始切换数据源..");
        // 方法优先，如果方法上存在注解，则优先使用方法上的注解
        if (signature.getMethod().isAnnotationPresent(DS.class)) {
            DynamicDataSourceContextHolder.setContextKey(signature.getMethod().getAnnotation(DS.class).value());
            // 其次类优先，如果类上存在注解，则使用类上的注解
            logger.info("切换数据源:" + signature.getMethod().getAnnotation(DS.class).value());
        } else if (aClass.isAnnotationPresent(DS.class)) {
            DynamicDataSourceContextHolder.setContextKey(aClass.getAnnotation(DS.class).value());
            // 如果都不存在，则使用默认
            logger.info("切换数据源:" + aClass.getAnnotation(DS.class).value());
        } else {
            DynamicDataSourceContextHolder.setContextKey(DataSourceConstants.DS_KEY_MASTER);
            logger.info("未配置,切换数据源:" + DataSourceConstants.DS_KEY_MASTER);
        }
        try {
            return joinPoint.proceed();
        } finally {
            DynamicDataSourceContextHolder.removeContextKey();
            logger.info("切换数据源结束..");
        }
    }
}
