package com.tuandai.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by 肖文 on 2018/4/9
 */
@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 批量添加String数据
     */
    public void insertString(Map map){
        redisTemplate.opsForValue().multiSet(map);
    }
}
