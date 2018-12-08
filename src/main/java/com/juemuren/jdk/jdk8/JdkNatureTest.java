package com.juemuren.jdk.jdk8;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 肖文 on 2018/11/12.
 * lambda表达式foreach性能测试
 * 结论：对于耗时的操作用lambda表达式的for循环，如数据库的IO操作，多线程充分利用CPU资源；
 * 对于不太耗时的操作使用普通for循环，比如纯CPU计算类型的操作，单线程性能更高，减少上下文切换的开销。
 */
public class JdkNatureTest {

    private List<String> list = new ArrayList<>();

    @Before
    public void init(){
        for (int i = 0; i < 10000000; i++) {
            list.add(String.valueOf(i));
        }
    }

    @Test
    public void testForeach(){
        long start = System.currentTimeMillis();
        for (Object s :list){
            s.toString();
        }
        long end = System.currentTimeMillis();
        System.out.println("forEach耗时："+(end-start) +"  ms");
    }

    @Test
    public void testLambda(){
        long start = System.currentTimeMillis();
        list.forEach(s -> {
            s.toString();
        });
        long end = System.currentTimeMillis();
        System.out.println("lambda耗时："+(end-start) +"  ms");
    }
}
