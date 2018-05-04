package com.tuandai;

import com.tuandai.config.AppConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableCaching
@MapperScan("com.tuandai.dao")
@Import(AppConfiguration.class)
//@EnableScheduling//使用定时任务需添加的配置
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
