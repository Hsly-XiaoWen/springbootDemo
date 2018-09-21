package com.juemuren.entiy;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 肖文 on 2018/5/6
 */
@ConfigurationProperties(prefix = "users")
public class Users {

    private List<User> user=new ArrayList<>();

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Users{" +
                "user=" + user +
                '}';
    }
}
