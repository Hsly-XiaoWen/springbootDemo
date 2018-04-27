package com.tuandai.mybatis;

import com.tuandai.entiy.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 肖文 on 2018/4/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheServiceTest {

    @Autowired
    private CacheService cacheService;

    @Test
    public void findUser() throws Exception {
        User user=cacheService.findUser(1);
        User user1 = cacheService.findUser(1);
        Assert.assertEquals(user.toString(),user1.toString());
    }

    @Test
    public void findUsers() throws Exception {
        cacheService.findUsers(2);
        cacheService.findUsers(2);
    }

    @Test
    public void deleteUser() throws Exception {
        cacheService.insertUser(cacheService.createUser(1));
        cacheService.deleteUser(1);
        cacheService.findUser(1);
    }

    @Test
    public void insertUser() throws Exception {
        cacheService.insertUsers(cacheService.createUser(1));
        cacheService.findUser(1);
    }

}