package com.juemuren.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.cache.*;
import com.juemuren.config.Globals;
import com.juemuren.entiy.Person;
import com.juemuren.restTemplate.RestTemplateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.concurrent.TimeUnit;

/**
 * Created by 肖文 on 2018/12/25.
 * 使用LoadingCache作为本地缓存
 */
@Service
public class LoadingCacheService {
    private static final Logger logger = LoggerFactory.getLogger(LoadingCacheService.class);
    private static final String MESSAGE_FORMAT = "请求第三方接口失败:%s";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Globals globals;

    /**
     * 使用本地缓存，设置超时时间为1分钟，超过时间会调用load方法往本地缓存添加对象
     */
    private final LoadingCache<String, Person> serviceOffCache = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.MINUTES)//设置时间对象没有被写访问则对象从内存中删除
            //移除监听器,缓存项被移除时会触发
            .removalListener(x->{
                //删除的时候触发
            })
            .build(new CacheLoader<String, Person>() {
                public Person load(String key) {
                    return getStaticsDataDTO();
                }
            });

    public Person getServiceOffCache() {
        try {
            return serviceOffCache.get("StaticsDataDTO");
        } catch (Exception e) {
            logger.error("获取基础配置异常:{}",e.getMessage());
        }
        return new Person();
    }

    /**
     * 调用存管的生成静态页面url
     */
    private Person getStaticsDataDTO() {
        Person person = new Person();
        try {
            URI uri = UriComponentsBuilder
                    .fromHttpUrl(globals.getManagement() + "/apm/setting/getApplogSetting").build().encode().toUri();
            String resultStr = restTemplate.postForObject(uri, RestTemplateUtils.applicationJsonUtf8(null),String.class);
            person = JSON.parseObject(resultStr,Person.class);
        } catch (Exception e) {
            logger.error("获取基础配置异常");
        }
        return person;
    }
}
