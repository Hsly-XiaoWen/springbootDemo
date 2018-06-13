package com.tuandai.utils;

import org.junit.Test;

/**
 * Created by 肖文 on 2018/6/13
 */
public class PropertiesUtilsTest {


    @Test
    public void key() throws Exception {
        System.out.println(System.getProperties());
        System.out.println(PropertiesUtils.key("user.name"));
        System.out.println(PropertiesUtils.key("user.dir"));
        String filePath = PropertiesUtils.key("user.dir") + "/src/main/resources/";
        System.out.println(filePath);
    }

    @Test
    public void getValueByKey() throws Exception {
        String filePath = PropertiesUtils.key("user.dir") + "/src/main/resources/Test.properties";
        System.out.println(filePath);
        filePath=filePath.replaceAll("\\\\", "/");
        String name = PropertiesUtils.GetValueByKey(filePath, "name");
        System.out.println(filePath+":"+name);
    }

}