package com.juemuren.config;

import com.juemuren.entiy.Routes;
import com.juemuren.entiy.User1;
import com.juemuren.entiy.Users;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 肖文 on 2018/5/4
 */
@Configuration
public class AppConfiguration {

    @Bean
    public Routes introduce() {
        return new Routes();
    }

    @Bean
    public Users initUsers(){
        return new Users();
    }

    @Bean
    public User1 initUser1(){
        return new User1();
    }

    @Bean
    @RefreshScope
    public Globals globals() {
        return new Globals();
    }
}