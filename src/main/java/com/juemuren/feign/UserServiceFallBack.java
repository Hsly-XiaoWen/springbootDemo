package com.juemuren.feign;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by 肖文 on 2018/6/19
 */
@Component
public class UserServiceFallBack implements UserService{

    @Override
    public List<Map> find() {
        System.out.println("UsersService.find调用失败");
        return null;
    }

    @Override
    public String get(String name,int age) {
        System.out.println("UsersService.find调用失败");
        return "UsersService.find调用失败";
    }

    @Override
    public String getHello() {
        return "UsersService.find调用失败";
    }

    @Override
    public String sayHello(String name) {
        return "UserService.sayHello调用失败";
    }
}
