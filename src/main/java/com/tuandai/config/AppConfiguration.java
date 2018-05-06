package com.tuandai.config;

import com.tuandai.entiy.Routes;
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
}
