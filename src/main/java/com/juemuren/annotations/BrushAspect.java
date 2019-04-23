package com.juemuren.annotations;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by 肖文 on 2019/1/3.
 */
@Aspect
@Component
public class BrushAspect {

    private static Logger logger = LoggerFactory.getLogger(BrushAspect.class);

    @Pointcut("@annotation(com.juemuren.annotations.BrushRedis)")
    public void brushRedis() {
        String  object = new String ();
    }

    @Around("brushRedis()")
    public void brush(ProceedingJoinPoint joinPoint){
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();

        BrushRedis brushRedis = method.getAnnotation(BrushRedis.class);
        if (brushRedis != null){

        }else{
            try {
                joinPoint.proceed();
            } catch (Throwable throwable) {
                logger.error("发生异常：{}",throwable.getMessage());
            }
        }
    }
}
