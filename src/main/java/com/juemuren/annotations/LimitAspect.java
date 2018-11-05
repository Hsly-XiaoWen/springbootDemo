package com.juemuren.annotations;

import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by 肖文 on 2018/10/13.
 */
@Component
@Scope
@Aspect
public class LimitAspect {

    ////每秒只发出5个令牌，此处是单进程服务的限流,内部采用令牌捅算法实现
    private static RateLimiter rateLimiter = RateLimiter.create(5.0);

    //Service层切点  限
    @Pointcut("@annotation(com.juemuren.annotations.SysLog)")
    public void ServiceAspect() {

    }


    @Around("ServiceAspect()")
    public  Object around(ProceedingJoinPoint joinPoint) {
        Boolean flag = rateLimiter.tryAcquire();
        Object obj = null;
        try {
            if(flag){
                obj = joinPoint.proceed();//继续进行后续流程，使用joinPoint进行拦截
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return obj;
    }
}
