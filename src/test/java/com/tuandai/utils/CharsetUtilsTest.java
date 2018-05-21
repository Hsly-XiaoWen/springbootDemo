package com.tuandai.utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 肖文 on 2018/5/21
 */
public class CharsetUtilsTest {

    private static final Logger logger = LoggerFactory.getLogger(CharsetUtilsTest.class);

    @Test
    public void changeCharset() throws Exception {
        String result = CharsetUtils.toASCII("肖文");
        String result1 =CharsetUtils.toISO_8859_1("肖文");
        String result2 =CharsetUtils.toUTF_8("肖文");
        String result3 =CharsetUtils.toGBK("肖文");
        String result4 = CharsetUtils.changeCharset("è\u0082\u0096æ\u0096\u0087", "ISO_8859_1", "UTF-8");
        logger.info("返回的数据是{}",result);
        logger.info("返回的数据是{}",result1);
        logger.info("返回的数据是{}",result2);
        logger.info("返回的数据是{}",result3);
        logger.info("返回的数据是{}",result4);
        logger.info("返回的数据是{}",System.getProperties());
    }

}