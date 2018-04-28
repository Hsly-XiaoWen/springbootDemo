package com.tuandai.service;

import org.springframework.stereotype.Service;

/**
 * Created by 肖文 on 2018/4/28
 */
@Service
public class TestServiceImpl implements TestInterface {

    @Override
    public String sayHello() {
        return "hello java";
    }
}
