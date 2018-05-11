package com.tuandai.mybatis;

import com.tuandai.entiy.User;
import com.tuandai.service.CacheService;
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
        User user=this.cacheService.findUser(1);
        User user1 = this.cacheService.findUser(1);
        Assert.assertEquals(user.toString(),user1.toString());
    }

    @Test
    public void findUsers() throws Exception {
        this.cacheService.findUsers(2);
        this.cacheService.findUsers(2);
    }

    @Test
    public void deleteUser() throws Exception {
        this.cacheService.insertUser(this.cacheService.createUser(1));
        this.cacheService.deleteUser(1);
        this.cacheService.findUser(1);
    }

    @Test
    public void insertUser() throws Exception {
        this.cacheService.insertUsers(this.cacheService.createUser(1));
        this.cacheService.findUser(1);
    }

}