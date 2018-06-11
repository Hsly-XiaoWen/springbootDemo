package com.tuandai.testng;

import org.junit.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by 肖文 on 2018/6/11
 * 分组测试
 */
public class TestNGDemo1 {

    @BeforeGroups("test1")
    public void init() {
        System.out.println("测试环境初始化=========");
    }

    @AfterGroups("test2")
    public void destroy() {
        System.out.println(1+"测试环境关闭=========");
    }

    @Test(groups = "test1")
    public void test() throws Exception {
        int i = 1 / 0;
        System.out.println(i+"test1测试方法组第一个方法111111111111");
    }

    @Test(groups = "test1",dependsOnMethods = "test")
    public void test1() {
        System.out.println("test1测试方法组第二个方法222222222");
    }

    @Test(groups = "test2")
    public void test2_1() {
        System.out.println("test2测试方法组第一个方法111111111111");
    }

    @Test(groups = "test2",dependsOnMethods = "test2_1")
    public void test2_2() {
        System.out.println("test2测试方法组第二个方法222222222");
    }

    private int i=0;
    @Test(dataProvider = "provideNumbers",threadPoolSize=3,invocationCount = 100000)
    public void tests(int number, int expected) {
        System.out.println(i++);
        Assert.assertEquals(number + 10, expected);
    }

    //DataProvider注解为测试方法提供提测数据
    @DataProvider(name = "provideNumbers")
    public Object[][] provideData() {

        return new Object[][] { { 10, 20 }, { 100, 110 }, { 200, 210 } };
    }
}
