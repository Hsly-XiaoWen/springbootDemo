package com.tuandai.utils;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by 肖文 on 2018/4/25
 * 公用方法
 */
public class CommonUtils {

    /**
     * 将数组转化为List
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> List<T> getList(T... array) {
        if (array == null || array.length == 0) {
            return new ArrayList<T>();
        }
        return new ArrayList<T>(Arrays.asList(array));
    }

    /**
     * 将数组转化为List
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> List<T> getLists(T[] array) {
        if (array == null || array.length == 0) {
            return new ArrayList<T>();
        }
        return new ArrayList<T>(Arrays.asList(array));
    }

    public static <T> List<T> toList(Collection<T> array) {
        if (array == null || array.size() == 0) {
            return new ArrayList<T>();
        }
        return new ArrayList<T>(array);
    }

    /**
     * 字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 判断集合是否为空
     */
    public static boolean isBlank(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断是否为空
     */
    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }


    /**
     * String转int
     *
     * @param str
     * @return
     */
    public static int getInt(String str,int defaultValue) {
        if (!isBlank(str)) {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e) {

            }
        }
        return defaultValue;
    }

    /**
     * 验证Email地址
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        return email != null && email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+");
    }

    /**
     * 版本号是按照v(\d{1,2}.\d{1,2}.\d{1,2})定义
     * 当出现1.1.1时候 补全成10101返回  用于比较版本号大小
     * @param version
     * @return
     */
    public static int completeVersion(String version) {
        StringBuilder builder = new StringBuilder();
        String[] arr = StringUtils.split(version,".");
        for (int i=0,size=arr.length;i<size;i++) {
            if (arr[i].length() == 1) {
                builder.append(0);
            }
            builder.append(arr[i]);
        }
        return getInt(builder.toString(), 0);
    }
}
