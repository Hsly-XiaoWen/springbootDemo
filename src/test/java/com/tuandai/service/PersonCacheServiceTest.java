package com.tuandai.service;

import com.alibaba.fastjson.JSON;
import com.tuandai.AppTest;
import com.tuandai.entiy.Person;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 肖文 on 2018/4/27
 */
public class PersonCacheServiceTest extends AppTest {

    @Autowired
    private PersonCacheService personCacheService;

    /**
     * 查询用户缓存测试
     * @throws Exception
     */
    @Test
    public void findPerson() throws Exception {
        Person result = this.personCacheService.findPerson(10000);
        logger.info("查询结果是{}", JSON.toJSON(result));
    }

    @Test
    public void updatePerson() throws Exception {
    }

    @Test
    public void deletePerson() throws Exception {
    }

}