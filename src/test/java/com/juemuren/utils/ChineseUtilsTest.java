package com.juemuren.utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 肖文 on 2018/5/20
 */
public class ChineseUtilsTest {

    private static final Logger logger = LoggerFactory.getLogger(ChineseUtilsTest.class);

    @Test
    public void getPinYing() throws Exception {
        String result = ChineseUtils.getPinYing("hello java,大家好我是小文");
        logger.info("返回的拼音是{}",result);
    }

    @Test
    public void chineseLength() throws Exception {
        int result = ChineseUtils.chineseLength("hello,我am啥事文");
        int result1 = ChineseUtils.chineseLength("");
        System.out.println(result);
        System.out.println(result1);
    }

}