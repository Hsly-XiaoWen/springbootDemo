package com.tuandai.utils;

import java.text.NumberFormat;

/**
 * Created by 肖文 on 2018/4/26
 */
public class MathUtils {

    /**
     * 取消小数点的后面的o 如 0.0500000 = 0.05
     * @param num
     * @return
     */
    public static String delNumZero(Double num) {
        NumberFormat nf = NumberFormat.getInstance();
        // 取消使用千分位
        nf.setGroupingUsed(false);
        try {
            return nf.format(num);
        } catch (NumberFormatException e) {
            return "0";
        }
    }

    /**
     * 取消小数点的后面的o 如 0.0500000 = 0.05
     * @param num
     * @return
     */
    public static String delNumZero(String num) {
        try {
            return delNumZero(Double.parseDouble(num));
        } catch (NumberFormatException e) {
            return "0";
        }
    }
}
