package com.juemuren.jdk8;

import com.juemuren.entiy.User;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by 肖文 on 2018/6/14
 * Java 8 API添加了一个新的抽象称为流Stream，可以让你以一种声明的方式处理数据
 * 这种风格将要处理的元素集合看作一种流， 流在管道中传输，
 * 并且可以在管道的节点上进行处理， 比如筛选， 排序，聚合等
 */
public class CollectionStreamDemo {

    private List<User> users;

    /**
     * Stream 提供了新的方法 'forEach' 来迭代流中的每个数据。
     */
    @Before
    public void init() {
        users = new ArrayList<User>() {
            {
                add(new User(1, "xiaowen1", 21, "xx"));
                add(new User(2, "xiaowen2", 21, "xx"));
                add(new User(3, "xiaowen3", 21, "xx"));
                add(new User(4, "xiaowen3", 22, "xx"));
            }
        };

    }

    public void test() {
        long count = users.stream().filter(x -> x.getId() == 21).count();
        System.out.println(count);
        //sorted 方法用于对流进行排序
        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);
    }

    /**
     * 双冒号相当于实现接口方法的具体实现
     */
    @Test
    public void test1() {
         //相当于users:forEach(x->System.out.println(x))
        users.forEach(System.out::println);
    }

    /**
     * Filter 过滤+ sorted 排序
     */
    @Test
    public void test2() {
        List<User> result = users.stream().filter(x -> x.getAge() == 21)
                .sorted(Comparator.comparing(User::getName).reversed()).collect(Collectors.toList());
        result.forEach(System.out::println);

        //过滤选择返回为true的数据集合
        List<User> us = users.stream().filter(x -> x.getAge() == 22).collect(Collectors.toList());
        us.forEach(System.out::println);
    }

    /**
     * 实现限流
     */
    @Test
    public void test3() {
        //limit 方法用于获取指定数量的流
        List<User> data = users.stream().limit(2).collect(Collectors.toList());
        data.forEach(System.out::println);

        List<User> result = users.stream().limit(2).filter(x->x.getAge()==22).collect(Collectors.toList());
        result.forEach(System.out::println);
    }

    /**
     * reduce对参数进行运算
     */
    @Test
    public void test4() {
        Optional<Integer> result = users.stream().limit(2).map(User::getAge).reduce((x1, x2) -> x1 + x2);
        result.ifPresent(System.out::println);
    }


    /**
     * map 不支持stream
     */
    @Test
    public void test5() {
        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            map.put(i, "val" + i);
        }

        map.forEach((key,value)->System.out.println(key+":"+value));

        map.merge(1, "test", (value, newValue) -> value.concat(newValue));
        map.forEach((key,value)->System.out.println(key+":"+value));
    }
}
