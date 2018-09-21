package com.juemuren.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 肖文 on 2018/4/26
 */
public class CommonUtilTest {

    /**
     * 测试数组转化
     */
    @Test
    public void testGetList(){
        List list=CommonUtils.getList("xiaowen", "xxx", "dasdsa", "dasdsa");
        list.forEach(x->System.out.println(x));
        String[] names = {"sadsa", "dsadas", "sdasd", "erwer"};
        list=CommonUtils.getLists(names);
        list.forEach(x->System.out.println(x));
        Collection collection = new ArrayList();
        collection.add("xasad");
        List lists = CommonUtils.toList(collection);
        lists.forEach(x->System.out.println(x));
    }

    /**
     * 判断是否为空
     */
    @Test
    public void testIsBlank() {
        boolean b=CommonUtils.isBlank(" ");
        boolean b1=CommonUtils.isBlank("");
        Collection collection = new ArrayList();
        collection.add("xasad");
        boolean c = CommonUtils.isBlank(collection);
        System.out.println(b);
        System.out.println(b1);
        System.out.println(c);
    }

    @Test
    public void testEmail(){
        boolean result = CommonUtils.isEmail("2418744127@qq.com");
        boolean result1 =CommonUtils.isEmail("2418744127@xiaowen.com");
        boolean result2=CommonUtils.isEmail("2418744127@hotmail.com");
        boolean result3= CommonUtils.isEmail("xiaowen@juemuren.com");
        System.out.println(result);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }
}
