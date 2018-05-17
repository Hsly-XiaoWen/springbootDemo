package com.tuandai.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;

/**
 * Created by 肖文 on 2018/5/17
 * 提供一些对象有效性校验的方法
 */
public class CheckUtils {

    private static Logger logger = LoggerFactory.getLogger(CheckUtils.class);

    /**
     * 判断String是否为空字符
     *
     * @param src
     * @return
     */
    public static boolean isBlank(String src) {
        return src == null || "".equals(src.trim());
    }

    /**
     * 判断一组字符串是否有效
     *
     * @param src
     * @return
     */
    public static boolean isBlank(String... src) {
        for (String s : src) {
            if (isBlank(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断Boolean类型，为空设置默认值
     *
     * @param value
     * @return
     */
    public static boolean validBoolean(Boolean value, boolean defValue) {
        if (value == null) {
            return defValue;
        }
        return value;
    }

    /**
     * 判断String值是否为数字
     *
     * @param value
     * @return
     */
    public static boolean isInteger(String value) {
        if (!isBlank(value)) {
            try {
                Integer.parseInt(value);
                return true;
            } catch (Exception e) {
                logger.info("类型转化失败{}", e.getMessage());
            }
        }
        return false;
    }

    /**
     * 判断对象是否有效
     *
     * @param object
     * @return
     */
    public static boolean validObject(Object object) {
        return object != null;
    }

    /**
     * 判断集合是否有效（不为空，有值）
     *
     * @param collection
     * @return
     */
    public static boolean validCollection(Collection collection) {
        return collection != null && !collection.isEmpty();
    }

    /**
     * 判断一组集合是否有效
     *
     * @param collection
     * @return
     */
    public static boolean validCollection(Collection... collection) {
        for (Collection c : collection) {
            if (!validCollection(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断map是否有效
     *
     * @param map
     * @return
     */
    public static boolean validMap(Map map) {
        return map != null && !map.isEmpty();
    }

    /**
     * 判断一组map是否有效
     *
     * @param map
     * @return
     */
    public static boolean validMap(Map... map) {
        for (Map m : map) {
            if (validMap(m)) {
                return false;
            }
        }
        return true;
    }
}

