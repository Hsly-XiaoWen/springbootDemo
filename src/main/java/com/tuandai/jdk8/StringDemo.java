package com.tuandai.jdk8;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

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

    /**
     * String.format()使用占位符
     */
    @Test
    public void formatTest(){
        String name = String.format("大家好,我是%S", "小文");
        System.out.println(name);
        //多个占位符时按照顺序匹配
        String s = String.format("Hello %s,%s,%s", "小文", "小华", ",welcome!");
        System.out.println(s);
        //多个占位符按照索引匹配
        s = String.format("Hello %2$s,%1$s,%3$s", "小文", "小华", ",welcome!");
        System.out.println(s);
        Date date = new Date();
        /**
         * 2018-05-15 14:35:40,需要先格式化日期，然后再格式化时间
         */
        String d = String.format("%tF %tT", date, date);
        System.out.printf(d);
    }
}
