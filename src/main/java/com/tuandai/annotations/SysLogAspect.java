package com.tuandai.annotations;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by 肖文 on 2018/9/13
 * 系统日志，切面处理类
 */
@Aspect
@Component
public class SysLogAspect {


    @Pointcut("@annotation(com.tuandai.annotations.SysLog)")
    public void logPointCut() {

    }

    @Before("logPointCut()")
    public void printLog(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        String value=null;
        SysLog sysLog = method.getAnnotation(SysLog.class);
        if (sysLog != null) {
            value = sysLog.value();
        }
        System.out.println(className+":"+methodName+":"+value);
    }
}
