package com.juemuren.utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 肖文 on 2018/4/26
 */
public class MathUtilsTest {

    private static final Logger logger = LoggerFactory.getLogger(MathUtils.class);

    /**
     * 测试去零
     * @throws Exception
     */
    @Test
    public void delNumZero() throws Exception {
        String result = MathUtils.delNumZero(0.50000);
        String result1=MathUtils.delNumZero("0.005000");
        logger.info("返回结果是{}",result);
        logger.info("返回结果是{}",result1);
    }

}