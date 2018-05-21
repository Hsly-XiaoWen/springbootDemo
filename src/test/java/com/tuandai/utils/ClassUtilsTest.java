package com.tuandai.utils;

import org.junit.Test;

/**
 * Created by 肖文 on 2018/5/21
 */
public class ClassUtilsTest {

    @Test
    public void getField() throws Exception {
        String[] result = ClassUtils.getField("com.tuandai.entiy.User", false);
        for (String str : result) {
            System.out.println(str);
        }
    }

    @Test
    public void getPublicField() throws Exception {
        String[] result = ClassUtils.getPublicField("com.tuandai.entiy.User", true);
        for (String str : result) {
            System.out.println(str);
        }
    }

    @Test
    public void getMethod() throws Exception {
        String[] result = ClassUtils.getMethod("com.tuandai.entiy.User", true);
        String[] result1 = ClassUtils.getPrivateMethod("com.tuandai.entiy.User");
        String[] result2 = ClassUtils.getPublicMethod("com.tuandai.entiy.User", true);
        for (String str : result2) {
            System.out.println(str);
        }
    }

}