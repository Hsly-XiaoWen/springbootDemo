package com.juemuren.utils;

import com.juemuren.entiy.User;
import com.juemuren.jdk8.LambdaDemo;
import org.junit.Test;

import java.util.*;

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
        CollectionUtils.handler(users,x->System.out.println(x));
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

    @Test
    public void union() throws Exception {
        List<String> list1 = new ArrayList<String>(){
            {
                add("AAA");
                add("BBB");
                add("CCC");
            }
        };
        Set<String> set1 = new HashSet<String>(){
            {
                add("AAA");
                add("BBB");
                add("CCC");
            }
        };
        Set<String> set2 = new HashSet<String>(){
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
        List<String> result = CollectionUtils.union(list1, list2);
        Collection<String> result1 = CollectionUtils.union(set1, set2);
        result.forEach(x->System.out.println(x));
        result1.forEach(x->System.out.println(x));
    }

    @Test
    public void join() throws Exception {
        List<String> list1 = new ArrayList<String>(){
            {
                add("AAA");
                add("BBB");
                add("CCC");
            }
        };
        Map<String, String> map = new HashMap<>();
        map.put("name", "xiaowen");
        map.put("age", "21");
        Map<String, User> userMap = new HashMap<>();
        userMap.put("xiaowen", new User(1, "xiaowen", 21, "aaa"));
        userMap.put("juemuren", new User(2, "juemuren", 21, "bbb"));
        map.forEach((k,v)->System.out.println(k+":"+v));
        users = new LambdaDemo().getUsers();
        String result = CollectionUtils.join(list1, "==");
        String result1 = CollectionUtils.join(users, "=", x -> x.getName());
        String result2 = CollectionUtils.join(map, "=","&");
        String result3 = CollectionUtils.valueJoin(userMap, "&",x->x.getName());
        String result4 = CollectionUtils.keyJoin(userMap, "&",x->x);
        System.out.println(result);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
    }
}