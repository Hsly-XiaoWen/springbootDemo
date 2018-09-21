package com.juemuren.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 肖文 on 2018/5/6
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ReadPropertyServiceTest {

    @Autowired
    private ReadPropertyService readPropertyService;

    /**
     * 使用@Value读取资源配置
     */
    @Test
    public void getTestString() throws Exception {
        this.readPropertyService.getTestString();
    }

    /**
     * 依赖Environment读取资源文件测试
     */
    @Test
    public void getString() throws Exception {
        this.readPropertyService.getString();
    }

    /**
     * 读取资源配置实体
     */
    @Test
    public void getUser1(){
        this.readPropertyService.getUser1();
    }

    /**
     * 读取资源配置实体集合
     */
    @Test
    public void getUsers(){
        this.readPropertyService.getUsers();
    }
}