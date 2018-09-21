package com.juemuren.utils;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 肖文 on 2018/5/17
 */
public class CheckUtilsTest {

    private Logger logger = LoggerFactory.getLogger(CheckUtilsTest.class);

    @Test
    public void isBlank() throws Exception {
        boolean result = CheckUtils.isBlank("");
        boolean result1 = CheckUtils.isBlank(" ");
        Assert.assertEquals(true,result);
        Assert.assertEquals(true,result1);
    }

    @Test
    public void isBlank1() throws Exception {
    }

    @Test
    public void validObject() throws Exception {
    }

    @Test
    public void validCollection() throws Exception {
        List list = new ArrayList();
        list.add("xxx");
        List list1 = new ArrayList();
        list1.add("xxx");
        boolean result = CheckUtils.validCollection(list);
        boolean result1 = CheckUtils.validCollection(list,list1);
        Assert.assertEquals(true,result);
        Assert.assertEquals(true,result1);
    }

    @Test
    public void validMap() throws Exception {
        Map map = new HashMap<>();
        map.put("name", "xxx");
        Map maps = new HashMap<>();
        boolean result = CheckUtils.validMap(map);
        boolean result1 = CheckUtils.validMap(map,maps);
        Assert.assertEquals(true,result);
        Assert.assertEquals(false,result1);
    }

}