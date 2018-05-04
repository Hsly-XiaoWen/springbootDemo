package com.tuandai.config;

import com.tuandai.entiy.Routes;
import org.springframework.context.annotation.Bean;

/**
 * Created by 肖文 on 2018/5/4
 */
public class AppConfiguration {

    @Bean
    public Routes introduce() {
        return new Routes();
    }
}
