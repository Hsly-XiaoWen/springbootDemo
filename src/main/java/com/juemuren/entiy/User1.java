package com.juemuren.entiy;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by 肖文 on 2018/5/6
 */
@ConfigurationProperties(prefix = "test.user")
public class User1 {
    private int id;
    private String name;
    private int age;
    private String love;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", love='" + love + '\'' +
                '}';
    }
}
