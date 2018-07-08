package com.tuandai.test;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 肖文
 * @Description ${DESCRIPTION}
 * @date 2018/3/30 11:50
 */
public class User{
    @JSONField(name = "UserId")
    private int userId;
    @JSONField(name = "Name")
    private String name;
    @JSONField(name = "PassWord")
    private String password;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
