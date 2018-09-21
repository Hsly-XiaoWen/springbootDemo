package com.juemuren.utils;

import java.io.UnsupportedEncodingException;

/**
 * Created by 肖文 on 2018/5/17
 * 编码相关的工具类
 */
public class CharsetUtils {

    /**
     * 7位ASCII字符，也叫作ISO646-US、Unicode字符集的基本拉丁块
     */
    public static final String US_ASCII = "US-ASCII";

    /**
     * ISO 拉丁字母表 No.1，也叫作 ISO-LATIN-1
     */
    public static final String ISO_8859_1 = "ISO-8859-1";

    /**
     * 8 位 UCS 转换格式
     */
    public static final String UTF_8 = "UTF-8";

    /**
     * 中文超大字符集
     */
    public static final String GBK = "GBK";

    /**
     * 更改str编码方式
     * @param str
     * @param newCharset
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String changeCharset(String str, String newCharset) throws UnsupportedEncodingException {
        if (CheckUtils.isBlank(str)) {
            return null;
        }
        byte[] bs = str.getBytes();
        return new String(bs, newCharset);
    }


    public static String toUTF_8(String str) throws UnsupportedEncodingException {
        return changeCharset(str, UTF_8);
    }

    public static String toASCII(String str) throws UnsupportedEncodingException {
        return changeCharset(str, US_ASCII);
    }

    public static String toISO_8859_1(String str) throws UnsupportedEncodingException {
        return changeCharset(str, ISO_8859_1);
    }

    public static String toGBK(String str) throws UnsupportedEncodingException {
        return changeCharset(str, GBK);
    }


    /**
     * 字符串编码转换的实现方法
     * @param str
     * @param oldCharset
     * @param newCharset
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String changeCharset(String str, String oldCharset,
                                       String newCharset) throws UnsupportedEncodingException {
        if (CheckUtils.isBlank(str)) {
            return null;
        }
        // 用旧的字符编码解码字符串。解码可能会出现异常。
        byte[] bs = str.getBytes(oldCharset);
        // 用新的字符编码生成字符串
        return new String(bs, newCharset);
    }
}
