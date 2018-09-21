package com.juemuren.service;

import com.juemuren.AppTest;
import com.juemuren.invocation.TestInvocationHandler;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Proxy;

/**
 * Created by 肖文 on 2018/4/28
 */
public class TestServiceImplTest extends AppTest{

    @Autowired
    private TestInterface testInterface;
    private TestInterface testService;

    @Before
    public void init(){
        TestInvocationHandler handler = new TestInvocationHandler(this.testInterface);
        testService= (TestInterface) Proxy.newProxyInstance(this.testInterface.getClass().getClassLoader(),
                this.testInterface.getClass().getInterfaces(), handler);
    }

    @Test
    public void sayHello() throws Exception {
        this.testService.sayHello();
    }

}