package com.tuandai.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by 肖文 on 2018/4/28
 */
@Async//在类上使用该注解表明所有方法异步调用，放于方法定义前该方法允许异步调用
@Service
public class TestServiceImpl implements TestInterface {

    @Override
    public String sayHello() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("异步调用完成hello java");
        return "异步调用完成hello java";
    }
}
