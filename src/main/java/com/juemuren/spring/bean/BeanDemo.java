package com.juemuren.spring.bean;

import com.alibaba.fastjson.JSON;

/**
 * Created by 肖文 on 2019/1/21.
 */
public class BeanDemo {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("返回数据是%s", JSON.toJSONString(this));
    }
}
