package com.tuandai.service;

import com.tuandai.entiy.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by 肖文 on 2018/4/23
 */
@Service
public class CacheService {

    private static final Logger logger = LoggerFactory.getLogger(CacheService.class);

    @Autowired
    private TestInterface testService;

    /**
     *
     * @param id
     * @return
     */
    @Cacheable(value = "user", key = "T(String).valueOf(#id)")
    public User findUser(int id) {
        this.testService.sayHello();
        logger.info("执行了findUser方法");
        User user = createUser(1);
        try {
            Thread.sleep(12000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("执行完成===========");
        return user;
    }

    /**
     * 对比findUser方法：需要使用返回参数才会把数据缓存
     * @param id
     */
    @Cacheable(value = "user", key = "T(String).valueOf(#id)")
    public void findUsers(int id) {
        logger.info("执行了findUser方法");
        User user = createUser(1);
    }

    @CacheEvict(value = "user", key = "T(String).valueOf(#id)")
    public void deleteUser(int id) {
        logger.info("删除了id、key为" + id + "的数据缓存");
    }

    @CachePut(value = "user",key = "T(String).valueOf(#user.id)")
    public User insertUser(User user) {
        logger.info("执行了createUser的方法");
        return user;
    }

    /**
     * 结论：涉及添加数据到缓存时候，把方法的返回值放到缓存
     * @param user
     */
    @CachePut(value = "user",key = "T(String).valueOf(#user.id)")
    public User insertUsers(User user) {
        logger.info("执行了createUser的方法");
        return user;
    }

    /**
     * 创建User对象
     * @param id
     * @return
     */
    public User createUser(int id) {
        logger.info("执行了创建对象的方法");
        User user = new User();
        user.setAge(11);
        user.setId(id);
        user.setName("大宝"+id);
        user.setLove("SP"+id);
        return user;
    }
}
