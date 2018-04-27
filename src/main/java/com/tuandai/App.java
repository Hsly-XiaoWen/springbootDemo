package com.tuandai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableCaching
@EnableScheduling//使用定时任务需添加的配置
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
