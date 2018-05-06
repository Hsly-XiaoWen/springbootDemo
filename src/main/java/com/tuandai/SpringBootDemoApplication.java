package com.tuandai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableCaching
@MapperScan("com.tuandai.dao")
@EnableAsync
@EnableScheduling//使用定时任务需添加的配置
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);

        //添加启动事件监听配置
       /* SpringApplication app = new SpringApplication(SpringBootDemoApplication.class);
        app.addListeners(new MyApplicationStartedEventListener());
        app.addListeners(new EnvironmentPreparedListener());
        app.addListeners(new ContextPreparedEventListener());
        app.addListeners(new FailedEventListener());
        app.run(args);*/
    }
}
