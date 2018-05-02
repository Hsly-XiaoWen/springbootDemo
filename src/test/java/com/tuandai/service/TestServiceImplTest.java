package com.tuandai.service;

import com.tuandai.AppTest;
import com.tuandai.invocation.TestInvocationHandler;
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
        TestInvocationHandler handler = new TestInvocationHandler(testInterface);
        testService= (TestInterface) Proxy.newProxyInstance(testInterface.getClass().getClassLoader(),
                testInterface.getClass().getInterfaces(), handler);
    }

    @Test
    public void sayHello() throws Exception {
        testService.sayHello();
    }

}