package com.tuandai.invocationHandler;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by 肖文 on 2018/4/28
 * 测试动态代理
 */
public class TestInvocationHandler implements InvocationHandler {

    private static final Logger logger = LoggerFactory.getLogger(TestInvocationHandler.class);

    private Object object;

    public TestInvocationHandler() {
    }

    public TestInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.info("请求的参数是{}", JSON.toJSON(args));
        Object result = method.invoke(object, args);
        logger.info("请求的参数是{}", JSON.toJSON(result));
        return result;
    }
}
