package com.tuandai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableCaching
@MapperScan("com.tuandai.dao")
//@EnableScheduling//使用定时任务需添加的配置
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
