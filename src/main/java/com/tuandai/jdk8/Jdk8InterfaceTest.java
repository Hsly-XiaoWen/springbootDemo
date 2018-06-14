package com.tuandai.jdk8;

import org.junit.Test;

/**
 * Created by 肖文 on 2018/6/14
 */
public class Jdk8InterfaceTest implements Jdk8Interface,Jdk8Interface1 {

    @Test
    public void test() {
        this.say();
        Jdk8Interface.run();
    }

    /**
     * 当实现类实现多个有默认方法的接口且方法名相同时候必须重写该方法
     * 使用接口.super.method()来财富重复使用接口方法默认实现
     */
    @Override
    public void say() {
        Jdk8Interface.super.say();
        Jdk8Interface1.super.say();
    }
}
