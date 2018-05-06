package com.tuandai.config;

import com.tuandai.entiy.Routes;
import com.tuandai.entiy.User1;
import com.tuandai.entiy.Users;
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
}
