package com.juemuren.spring;

import org.junit.Test;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

/**
 * Created by 肖文 on 2019/1/21.
 * 测试使用SpringUtils方法
 */
public class SpringUtilsDemo {

    @Test
    public void substringMatch(){
        System.out.println(StringUtils.substringMatch("xiaowen",4,"wen"));
        System.out.println("juemuren".indexOf("mur"));
        System.out.println(LocalDate.of(2019,01,21).toEpochDay()-LocalDate.now().toEpochDay());
    }
}
