package com.tuandai.utils;

import com.tuandai.entiy.User;
import com.tuandai.jdk8.LambdaDemo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 肖文 on 2018/5/17
 */
public class CollectionUtilsTest {

    private List<User> users;

    /**
     *  对集合进行进行特定处理
     * @throws Exception
     */
    @Test
    public void handler() throws Exception {
        users = new LambdaDemo().getUsers();
        CollectionUtils.handler(users, x->System.out.println(x));
    }


    /**
     * CollectionUtils.process测试
     * @throws Exception
     */
    @Test
    public void process() throws Exception {
        users = new LambdaDemo().getUsers();
        List<User> us=new ArrayList<User>();
        us.add(new User(10, "juemuren2", 21, "xx"));
        us.add(new User(11, "juemuren1", 21, "xx"));
        CollectionUtils.process(us,users,x->new User(x.getId(),x.getName(),x.getAge(),x.getLove()));
        CollectionUtils.handler(users,x->System.out.println(x.toString()));
    }

    /**
     * 删除重复元素测试
     */
    @Test
    public void removeElement() {
        users = new LambdaDemo().getUsers();
        users.add(new User(1, "xiaowen1", 21, "xx"));
        users.forEach(x->System.out.println(x.toString()));
        System.out.println();
        CollectionUtils.removeElements(users);
        users.forEach(x->System.out.println(x.toString()));
    }

    /**
     * 取交集测试
     */
    @Test
    public void intersection() {
        String a = "AAA";
        String b = "BBB";
        String c = "CCC";
        List<String> list1 = new ArrayList<String>(){
            {
                add("AAA");
                add("BBB");
                add("CCC");
            }
        };
        List<String> list2 = new ArrayList<String>(){
            {
                add("AAA");
                add("BBB");
            }
        };
        List<String> result = CollectionUtils.intersection(list1, list2);
        result.forEach(x -> System.out.println(x.toString()));
    }
}