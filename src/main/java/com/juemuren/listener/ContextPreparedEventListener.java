package com.juemuren.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;

/**
 * 上下文创建完成后执行的事件监听器
 * Created by 肖文 on 2018/5/6
 */
public class ContextPreparedEventListener implements ApplicationListener<ApplicationPreparedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ContextPreparedEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent applicationPreparedEvent) {
        logger.info("上下文创建完成后执行的事件监听器{}", applicationPreparedEvent);
    }
}
