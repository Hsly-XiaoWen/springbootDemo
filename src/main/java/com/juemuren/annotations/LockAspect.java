package com.juemuren.annotations;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 肖文 on 2018/9/30
 */
@Component
@Aspect
@Order(1)
public class LockAspect {

    private static Lock lock = new ReentrantLock(true);

    @Pointcut("@annotation(com.juemuren.annotations.SysLog)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public  Object around(ProceedingJoinPoint joinPoint) {
        lock.lock();
        Object obj = null;
        try {
            //执行
            obj = joinPoint.proceed();
            System.out.println(obj);
        } catch (Throwable e) {
            e.printStackTrace();
        } finally{
            lock.unlock();
        }
        return obj;
    }
}
