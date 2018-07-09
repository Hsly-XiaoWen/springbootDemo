package com.tuandai.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by 肖文 on 2018/7/9
 */
public class JsonDemo {

    private Product product;
    @Before
    public void init() {
        product = new Product();
        product.setAge("21");
        product.setDesc("男");
        product.setName("xiaowen");
    }

    @Test
    public void testJson() {
        String jsonString = JSON.toJSONString(product);
        System.out.println(jsonString);
//        jsonString = "{\"Age\":\"21\",\"Desc\":\"男\",\"Name\":\"xiaowen\"}";
        jsonString=jsonString.toUpperCase();
        System.out.println(jsonString);
        Product result = JSONObject.toJavaObject(JSONObject.parseObject(jsonString), Product.class);
//        Product result = JSONObject.parseObject(jsonString, Product.class);
        System.out.println(result.getDesc());
    }
}

class Product {
    private String name;
    private String age;
    private String desc;

    @JSONField(name = "NAME")
    public String getName() {
        return name;
    }

    @JSONField(name = "Name")
    public void setName(String name) {
        this.name = name;
    }

    @JSONField(name = "AGE")
    public String getAge() {
        return age;
    }

    @JSONField(name = "Age")
    public void setAge(String age) {
        this.age = age;
    }

    @JSONField(name = "DESC")
    public String getDesc() {
        return desc;
    }

    @JSONField(name = "Desc")
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
