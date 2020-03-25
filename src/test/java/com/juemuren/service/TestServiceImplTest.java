package com.juemuren.service;

import com.juemuren.AppTest;
import com.juemuren.invocation.JdkProxyUtils;
import com.juemuren.invocation.TestInvocationHandler;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Proxy;

/**
 * Created by 肖文 on 2018/4/28
 */
public class TestServiceImplTest extends AppTest {

    @Autowired
    private TestServiceImpl testInterface;
    private TestInterface testService;

    @Test
    public void sayHello() throws Exception {
        TestInvocationHandler handler = new TestInvocationHandler(this.testInterface);
        testService = (TestInterface) Proxy.newProxyInstance(this.testInterface.getClass().getClassLoader(),
                this.testInterface.getClass().getInterfaces(), handler);
        this.testService.sayHello();
    }


    @Test
    public void proxyUtils() throws Exception {
        TestInterface testInterface1 = (TestInterface) JdkProxyUtils.newProxyInstance(this.testInterface);
        testInterface1.sayHello();
    }

    @Test
    public void testRetry() throws InterruptedException {
        this.testInterface.testRetry();
        Thread.sleep(10000);
    }
}