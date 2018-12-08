package com.juemuren.config.ds;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

/**
 * Created by 肖文 on 2018/12/8.
 */
@Aspect
@Order(11)
public class DbSourceInterceptor {

    @Pointcut(value = "execution(public * com.juemuren.service.*.*(..))")
    public void any() {

    }

    @Around("@annotation(ds)")
    public Object proceed(ProceedingJoinPoint pjp, DataSourceName ds) throws Throwable {
        System.out.println("执行了该方法");
        DataSourceHolder.putServiceDataSource(ds.value());
        try {
            return pjp.proceed();
        } finally {
            DataSourceHolder.removeServiceDataSource(ds.value());
        }
    }
}
