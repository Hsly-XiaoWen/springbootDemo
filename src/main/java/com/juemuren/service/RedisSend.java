package com.juemuren.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by 肖文 on 2018/11/6.
 */
@Service
public class RedisSend {

    @Autowired
    private RedisTemplate redisTemplate;
    //向通道发送消息的方法
    public void sendChannelMess(String channel, String message) {
        redisTemplate.convertAndSend(channel, message);
    }
}
