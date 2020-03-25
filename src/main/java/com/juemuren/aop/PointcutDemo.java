package com.juemuren.aop;

import com.juemuren.service.TestInterface;
import com.juemuren.service.TestServiceImpl;
import org.aspectj.weaver.Advice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Method;

/**
 * Pointcut具体实现名字匹配
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PointcutDemo {

    @Test
    public void NameMatchMethodPointcutDemo(){
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("xiaowen");

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);


        BeforeAdvice advice = (MethodBeforeAdvice) (method, objects, target) -> System.out.println(target.getClass().getSimpleName() + ":" + method.getName() + " - before logic ");
        advisor.setAdvice(advice);

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(new TestServiceImpl());
        factory.addAdvice(advice);

        TestInterface object = (TestInterface) factory.getProxy();
        object.sayHello();
    }

}
