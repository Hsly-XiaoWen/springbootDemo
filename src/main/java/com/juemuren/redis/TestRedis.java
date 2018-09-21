package com.juemuren.redis;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by 肖文 on 2018/4/9
 */


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() throws Exception {
        redisTemplate.opsForValue().set("aaa", "111");
        Map<String, String> maps = new HashMap<String, String>();
        maps.put("name", "xiaowen");
        maps.put("sex", "男");
        List<String> keys = new ArrayList<String>();
        keys.add("name");
        keys.add("sex");
        redisTemplate.opsForValue().multiSet(maps);
//        redisTemplate.opsForValue().set();//添加缓存并设置过期时间
        List data = redisTemplate.opsForValue().multiGet(keys);
        Assert.assertEquals("男", data.get(1));
    }

    @Test
    public void test1(){
        List<String> names = new ArrayList<String>();
        for (int i=0;i<5;i++) {
            names.add("xiaowen" + i);
        }

        redisTemplate.opsForList().leftPush("namexw", names);
        List<String> lists = redisTemplate.opsForList().range("namexw", 0, -1);
        System.out.print(lists);
        String data = lists.get(0);
        System.out.print(data.split(","));
    }

    @Test
    public void testMap(){
        HashOperations ops = redisTemplate.opsForHash();
        Map<String, String> maps = new HashMap<String, String>();
        maps.put("school", "jxlg");
        maps.put("sex", "boy");
        ops.putAll("maps", maps);
        Object data = ops.get("maps", "school");
        Assert.assertEquals("jxlg",data);
    }

    @Test
    public void testGetMap() {
        redisTemplate.expire("maps", 10, TimeUnit.SECONDS);
        Object object = redisTemplate.opsForHash().get("maps", "sex");
        System.out.println(object.toString());
    }
}
