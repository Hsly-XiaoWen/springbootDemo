package com.juemuren.testng;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * Created by 肖文 on 2018/6/11
 */
public class TestNGDemo {

    private final Logger logger = LoggerFactory.getLogger(TestNGDemo.class);

    @BeforeSuite
    public void init() {
        logger.info("@BeforeSuite注解在该套件的所有测试都运行在注释的方法之前，仅运行一次");
    }

    @Test(enabled = true,timeOut = 5000)
    public void method() throws InterruptedException{
        Thread.sleep(4000);
        System.out.println("执行的第一个方法");
    }

    //依赖测试，在保证dependsOnMethods方法执行完成的情况下进行当前方法
    @Test(dependsOnMethods = "method")//依赖方法是当前类所有的方法
    public void method1() {
        System.out.println("执行的第二个方法");
    }

    //测试代码中的预期异常抛出
    @Test(expectedExceptions = ArithmeticException.class)
    public void divisionWithException() {
        int i = 1 / 0;
        System.out.println("After division the value of i is :"+ i);
    }
}
