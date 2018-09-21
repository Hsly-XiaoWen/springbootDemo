package com.juemuren.utils;

/**
 * Created by 肖文 on 2018/5/17
 * 邮件相关的工具类
 */
public class EmailUtil {

    /**
     * 验证Email地址
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        return email != null && email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+");
    }

    /**
     * 验证多个Email地址
     * @param emails
     * @param seperator
     * @return
     */
    public static boolean isEmail(String emails, String seperator) {
        if (CommonUtils.isBlank(emails) || CommonUtils.isBlank(seperator)){
            return false;
        }
        for(String e : emails.split(seperator)) {
            if(!isEmail(e)) return false;
        }
        return true;
    }
}
