package com.tuandai.jdk8;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 肖文 on 2018/5/4
 * String方法使用
 */
public class StringDemo {

    private static final Logger logger = LoggerFactory.getLogger(StringDemo.class);

    @Test
    public void testStartWith(){
        String name = "name:xiaowen age:22 sex:boy";
        boolean result = name.startsWith("name");
        boolean result1 = name.startsWith("name",1);
        logger.info("返回数据是{}，result1{}",result,result1);

        /**
         * split第二个参数，limit=0无限次的截取，limit>0规定截取次数 eg:limit=1 截取一次
         */
        String[] result2 = name.split(":",0);
        for (String str:result2) {
            System.out.println(str+"===");
        }

        /**
         * substring()返回字符串的子字符串
         * 从0到name.length-1
         * name.substring(4,10) 包含第四位，不包含第10位
         */
        String result3 = name.substring(4);
        String result4 = name.substring(4,10);
        String result5 = new String(name.toCharArray(), 4, 6);
        logger.info("返回数据是result3{}，result4{}",result3,result4);
        logger.info("返回数据是result5{}",result5);
    }
}
