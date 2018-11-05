package com.juemuren.jdk.jdk8;

/**
 * Created by 肖文 on 2018/6/14
 * 接口实现默认方法，可以为具体实现类提供公共代码
 * 当接口默认实现不能满足需求时，实现类重写该方法
 */
public interface Jdk8Interface {

    default void say() {
        System.out.println("this is jdk interface demo");
    }

    /**jdk8接口可以声明（并且可以提供实现）静态方法
     * 接口静态方法只能通过接口名.方法名来实现调用
     */
    static void run() {
        System.out.println("this is jdk static method");
    }
}
