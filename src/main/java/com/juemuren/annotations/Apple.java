package com.juemuren.annotations;

import org.junit.Test;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * Created by 肖文 on 2018/7/10
 */
@FruitColor(name = "苹果",address = "山东")
public class Apple {

    @FruitColor(value = "YELLOW")
    private String color;

    @FruitColor(value = "YELLOW")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Test
    public void test() {
        FruitColor result = null;
        FruitColor result1 = null;
        FruitColor result2 = null;
        try {
            //读取方法上的注解
            result = AnnotationUtils.findAnnotation(Apple.class.getMethod("getColor"), FruitColor.class);
            //读取属性上的注解
            result1 = AnnotationUtils.findAnnotation(Apple.class.getDeclaredField("color"), FruitColor.class);
            //获取类上的注解
            result2 = AnnotationUtils.findAnnotation(Apple.class,FruitColor.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result.value());
        System.out.println(result1.value());
        System.out.println(result2.value()+":"+result2.name()+":"+result2.address());
    }
}
