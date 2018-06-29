package com.tuandai.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 肖文 on 2018/5/11
 */
public class TestModelAndMap {

    /**
     * 测试对象转转化为JSON和Map转化为JSON的区别
     */
    @Test
    public void test(){
        User user = new User();
        user.setName("xiaowen");
        user.setPassword("123456a");
        Object obj=JSON.toJSON(user);
        Map<String, Object> params = new HashMap<>();
        params.put("name", "xiaowen");
        params.put("password", "123456a");
        Object obj1 = JSON.toJSON(params);
        System.out.println(obj.toString());
        System.out.println(obj1.toString());
    }

    /**
     * compareTo方法返回值测试
     * compareTo方法通过与参数对比，返回比较的值
     */
    @Test
    public void testCompareTo() {
        String name = "xiaowen";
        int result = name.compareTo("kiaowen");
        System.out.println(result);
    }
}
