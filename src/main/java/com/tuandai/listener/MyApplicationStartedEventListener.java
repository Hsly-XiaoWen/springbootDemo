package com.tuandai.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by 肖文 on 2018/5/6
 *
 */
public class MyApplicationStartedEventListener implements ApplicationListener<ApplicationStartingEvent> {

    private static final Logger logger = LoggerFactory.getLogger(MyApplicationStartedEventListener.class);
    @Override
    public void onApplicationEvent(ApplicationStartingEvent applicationStartingEvent) {
        SpringApplication result = applicationStartingEvent.getSpringApplication();
        logger.info("ApplicationStartingEvent事件的执行时间{}",result);
    }
}
