package com.juemuren.jdk8;

import com.alibaba.fastjson.JSON;
import com.juemuren.dto.Page;
import com.juemuren.entiy.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by 肖文 on 2018/4/23
 */
public class LambdaDemo {

    private static final Logger logger = LoggerFactory.getLogger(LambdaDemo.class);

    public static void main(String[] args) {
    }

    /**
     * 使用Lambda替换匿名内部类
     */
    private static void testThread() {
        new Thread(() -> System.out.println("In Java8, Lambda expression rocks !!")).start();
    }

    public void testOnePar(LambdaInterface lambdaInterface){
        lambdaInterface.test("msg");
    }

    /**
     * 使用Function函数式接口，返回另外一个任意类型返回值
     * @param function
     * @return
     */
    public Object testFunction(Function function) {
        return function.apply(new User(1, "juemuren", 22, "yqq"));
    }

    public void testPredicate(int n,Predicate<Integer> predicate) {
        if (predicate.test(n)){
            System.out.println("执行该方法");
        }
    }
    /**
     * 使用的jdk自带的函数式接口
     * 主要作用：执行副作用操作（改变参数的内部状态）
     */
    @Test
    public void testConsumer(){
        this.testPredicate(4,n -> n>3);
        User user=new User();
        Consumer<User> consumer=user1 -> user1.setName("xiaowen");
        consumer.accept(user);
        logger.info("返回的user{}",user.toString());
    }
    public void testConsumer1(User user,Consumer<User> consumer){
        consumer.accept(user);
        logger.info("返回的user{}",user.toString());
    }


    /**
     * jdk8自带的函数式接口lambda测试
     */
    @Test
    public void testMyFunctionalInterface(){
        testOnePar(x->System.out.println(x));
        User user=(User)testFunction(x->new User(11, "juemuren1", 222, "yqq2"));
        User user1 = new User();
        this.testConsumer1(user1,users->users.setName("yqq"));
        logger.info("返回的数据是{}",user.toString());
    }

    @Test
    public void testCollection() {
        List<String> strs = new ArrayList<String>() {
            {
                add("a");
                add("c");
                add("g");
                add("b");
                add("d");
            }
        };
        List<Integer> nums = new ArrayList<Integer>() {
            {
                add(10);
                add(5);
                add(62);
                add(50);
                add(1);
            }
        };

        List<User> users = this.getUsers();

        Collections.sort(users,(s1,s2)->s1.getId()-s1.getId());
        Collections.sort(strs, (s1, s2) -> s1.compareTo(s2));
        Collections.sort(nums,(s1,s2)->s1-s2);
        strs.forEach(x->System.out.println(x));
        strs.forEach(System.out::println);
    }

    /**
     * 得到List对象进行过滤出来得到新的List对象
     * 使用lambda表达式
     */
    @Test
    public void testUser() {
        Page<User> us=new Page<>();
        List<User> list;
        list = this.getUsers().stream().filter(x -> x.getName().contains("xiaowen"))
                .map(m -> new User(m.getId(), m.getName(), m.getAge(), m.getLove()))
                .collect(Collectors.toList());
        list.forEach(x->System.out.println(x.toString()));
        us.setCount(list.size());
        us.setData(list);
        logger.info("返回的结果是{}", JSON.toJSONString(us));
    }


    public List<User> getUsers(){
        List<User> users=new ArrayList<User>(){
            {
                add(new User(1, "xiaowen1", 21, "xx"));
                add(new User(2, "xiaowen2", 21, "xx"));
                add(new User(3, "xiaowen3", 21, "xx"));
                add(new User(4, "xiaowen4", 21, "xx"));
                add(new User(5, "xiaowen5", 21, "xx"));
                add(new User(6, "xiao6", 21, "xx"));
                add(new User(7, "xiao7", 21, "xx"));
            }
        };
        return users;
    }
}
