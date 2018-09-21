package com.juemuren.jdk8;

/**
 * Created by 肖文 on 2018/6/14
 * 接口实现默认方法，可以为具体实现类提供公共代码
 * 当接口默认实现不能满足需求时，实现类重写该方法
 */
public interface Jdk8Interface1 {

    default void say() {
        System.out.println("这是测试一个实现类实现多个默认接口方法");
    }

}
