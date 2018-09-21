package com.juemuren.utils;

import com.juemuren.test.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 肖文 on 2018/4/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilsTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * redisTemplate设置超时时间
     * @throws Exception
     */
    @Test
    public void putForHash() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(30);
        RedisUtils.putForHashs(redisTemplate,"hello redis","test","test2",RedisUtils.expire(localDateTime));
    }

    /**
     * 测试使用BoundHashOperations设置超时时间
     * @throws Exception
     */
    @Test
    public void putForHashs() throws Exception {
        User user = new User("xiaowen", "1222");
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(30);
        RedisUtils.putForHash(redisTemplate,user,"maps","demo1",RedisUtils.expire(localDateTime));
    }

    /**
     * 批量添加Hash
     * @throws Exception
     */
    @Test
    public void putForAllHash() throws Exception {
        Map<String, String> maps = new HashMap<String, String>();
        maps.put("name", "xiaowen");
        maps.put("age", "21");
        maps.put("school", "jxlg");
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(30);
        RedisUtils.putForAllHash(redisTemplate,maps,"map",RedisUtils.expire(localDateTime));
    }

    /**
     * 没有主key添加
     */
    @Test
    public void putForValue() {
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(30);
        RedisUtils.putForValue(redisTemplate,new User(),"value",RedisUtils.expire(localDateTime));
    }

    /**
     * 有主key添加
     */
    @Test
    public void putForValues() {
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(30);
        RedisUtils.putForValue(redisTemplate,new User("xiaowen","123456"),"user","value",RedisUtils.expire(localDateTime));
    }

    /**
     * List添加
     */
    @Test
    public void putForList(){
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(30);
        List<User> list = new ArrayList<>();
        list.add(new User("xxx", "111"));
        list.add(new User("qqq", "111"));
        list.add(new User("aaa", "111"));
        list.add(new User("sss", "111"));
        RedisUtils.putForList(redisTemplate, list, "list", RedisUtils.expire(localDateTime));
    }

    @Test
    public void getForHash(){
        User name=RedisUtils.getForHash(redisTemplate, "maps", "demo1", User.class);
        Integer test=RedisUtils.getForHash(redisTemplate, "map", "age", Integer.class);
        String names=RedisUtils.getForHash(redisTemplate, "test", "test2", String.class);
        System.out.println(name);
        System.out.println(test);
        System.out.println(names);
    }
}