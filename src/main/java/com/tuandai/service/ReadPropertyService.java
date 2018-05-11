package com.tuandai.service;

import com.tuandai.entiy.User1;
import com.tuandai.entiy.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * Created by 肖文 on 2018/5/6
 * 资源配置读取
 */
@Service
public class ReadPropertyService {

    @Value("${test.name}")
    private String name;
    @Value("${test.age}")
    private String age;
    @Value("${test.user.name}")
    private String city;
    @Autowired
    private Environment environment;
    @Autowired
    private Users users;
    @Autowired
    private User1 user1;

    /**
     * 使用@Value读取资源配置信息
     */
    public void getTestString(){
        System.out.println(this.name+":" + this.age +  ":" + this.city);
    }

    /**
     * 使用Environment来读取信息
     */
    public void getString(){
        String result = this.environment.getProperty("test.user.name");
        System.out.println(result);
    }

    /**
     * 获取资源配置实体
     */
    public void getUser1(){
        System.out.println(this.user1.toString());
    }
    /**
     * 读取配置实体集合
     */
    public void getUsers(){
        this.users.getUser().forEach(x->System.out.println(x.toString()));
    }
}
