package com.juemuren.jdk.jdk8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 肖文 on 2018/6/30
 */
public class JavaBetter {

    @Test
    public void test1() {
        int n=6;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        System.out.println(n+1);

        //使用移位符操作
        int s=6;
        System.out.println((s<<1)+s);
        int a=10;
        a = a + (a >> 1);
        System.out.println(a);
    }


    @Test
    public void test() {
        ArrayList<String> list = new ArrayList<>(3);
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        for (int i=0;i<size(list);i++) {
            System.out.println(i);
            list.get(i).startsWith("a");
            list.get(i).charAt(0);
        }
        HashMap<Integer, String> maps = new HashMap<>(4);
        maps.put(1,"aaa");
    }

    private int size(List<String> list) {
        System.out.println("------------------------");
        return list.size();
    }
}
