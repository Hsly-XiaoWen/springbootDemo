package com.juemuren;

import com.juemuren.restTemplate.OkHttpConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试基类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

    protected static final Logger logger = LoggerFactory.getLogger(AppTest.class);
    @Autowired
    private OkHttpConfig okHttpConfig;

    @Test
    public void test(){
        logger.info("返回数据是{}",okHttpConfig.toString());
    }
}
