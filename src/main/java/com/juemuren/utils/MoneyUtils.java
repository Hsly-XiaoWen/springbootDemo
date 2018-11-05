package com.juemuren.utils;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by 肖文 on 2018/10/25.
 * BigDecimal.ROUND_DOWN：直接删除多余的小数 eg:2.35->2.3
 * BigDecimal.ROUND_UP：进位处理 eg:2.35->2.4
 * ROUND_CEILING：接近正无穷的舍入
 * ROUND_FLOOR:接近负无穷大的舍入模式。
 * BigDecimal.ROUND_HALF_UP：四舍五入，向上取值 eg：2.35->2.4
 * BigDecimal.ROUND_HALF_DOWN：五舍六入,向下舍弃 eg：2.35->2.3，2.351->2.4
 * BigDecimal.ROUND_HALF_EVEN: 临近取舍，舍弃的前一位为奇数：等同于四舍五入，舍弃的前一位为偶数：等同于五舍六入
 *                             eg: 2.255->2.25,     2.465->2.65
 * ROUND_UNNECESSARY
 */
public class MoneyUtils {

    /**
     * String转BigDecimal
     * @param money 字符串金额
     * @param scale 保留小数位
     * @param roundingMode 舍取方式
     * @return
     */
    public static BigDecimal toDecimal(String money, int scale,int roundingMode) {
        if (StringUtils.isBlank(money)) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(money).setScale(scale, roundingMode);
    }

    /**
     * String格式金额转化为BigDecimal 四舍五入保留两位小数
     * scale=2 1.255-》1.26  1.266->1.27 四舍五入向上取
     * @param money 字符串金额
     * @param scale 小数位
     * @return
     */
    public static BigDecimal toDecimal(String money, int scale) {
        return toDecimal(money, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * String格式金额转化为BigDecimal 四舍五入默认保留两位小数
     * 1.285-》1.29
     * money为空时，返回0
     * @param money
     * @return
     */
    public static BigDecimal toDecimal(String money) {
        return toDecimal(money, 2);
    }

    /**
     * String格式金额安全转化为BigDecimal 截取保留小数
     * 1.256-》1.25 截取多余小数位
     * @param money 金额
     * @param scale 小数位
     * @return
     */
    public static BigDecimal toTruncate(String money, int scale) {
        return toDecimal(money, scale, BigDecimal.ROUND_DOWN);
    }

    /**
     * String格式金额安全转化为BigDecimal 截取保留2位小数
     * 1.256-》1.25 截取多余小数位
     * @param money 金额
     * @return
     */
    public static BigDecimal toTruncate(String money) {
        return toTruncate(money, 2);
    }

    /**
     * 保留X位小数 四舍五入
     *  1.255->1.26
     * @param decimal 金额
     * @param scale 小数保留位数
     * @return
     */
    public static BigDecimal toMoney(BigDecimal decimal, int scale) {
        if (decimal == null || decimal.compareTo(BigDecimal.ZERO) < 0) {
            return BigDecimal.ZERO;
        }
        return decimal.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 默认保留2位小数 四舍五入
     * 1.255->1.26
     * @param decimal 金额
     * @return
     */
    public static BigDecimal toMoney(BigDecimal decimal) {
        return toMoney(decimal, 2);
    }

    /**
     * BigDecimal四舍五入保留scale位转String（不去零）
     * 1.205-》1.21 1.2005->1.20
     * @param decimal 金额
     * @param scale 小数位
     * @return
     */
    public static String toStrMoney(BigDecimal decimal,int scale) {
        return toMoney(decimal,scale).toPlainString();
    }

    /**
     * BigDecimal四舍五入保留2位转String（不去零）
     * 1.205-》1.21 1.2005->1.20
     * @param decimal 金额
     * @return
     */
    public static String toStrMoney(BigDecimal decimal) {
        return toStrMoney(decimal,2);
    }

    /**
     * 先四舍五入去除多余0，最多保留scale位小数
     * 1.2000->1.2 1.255000->1.26
     * @param decimal 金额
     * @param scale 小数位
     * @return
     */
    public static String moneyDelZero(BigDecimal decimal,int scale) {
        decimal = defVal(decimal);
        return toMoney(decimal,scale).stripTrailingZeros().toPlainString();
    }

    /**
     * 四舍五入去除多余0，最多保留2位小数
     * 1.2000->1.2 1.255000->1.26
     * @param decimal
     * @return
     */
    public static String moneyDelZero(BigDecimal decimal) {
        return moneyDelZero(decimal,2);
    }

    /**
     * 截取保留scale位小数
     *  1.256->1.25
     * @param decimal
     * @param scale
     * @return
     */
    public static BigDecimal truncate(BigDecimal decimal, int scale) {
        if (decimal == null || decimal.compareTo(BigDecimal.ZERO) < 0) {
            return BigDecimal.ZERO;
        }
        return decimal.setScale(scale, BigDecimal.ROUND_DOWN);
    }

    /**
     * 截取保留2位小数
     * 1.256->1.25
     * @param decimal
     * @return
     */
    public static BigDecimal truncate(BigDecimal decimal) {
        return truncate(decimal,2);
    }

    /**
     * 截取X位小数
     * @param decimal
     * @return
     */
    public static String truncateStr(BigDecimal decimal, int scale) {
        return truncate(decimal, scale).toPlainString();
    }

    /**
     * 截取2位小数
     * @param decimal
     * @return
     */
    public static String truncateStr(BigDecimal decimal) {
        return truncateStr(decimal,2);
    }

    /**
     * 截取x位小数再去除多余0
     * @param decimal
     * @return
     */
    public static String truncateDelZero(BigDecimal decimal,int scale) {
        decimal = defVal(decimal);
        return truncate(decimal,scale).stripTrailingZeros().toPlainString();
    }

    /**
     * 截取2位小数再去除多余0
     * @param decimal
     * @return
     */
    public static String truncateDelZero(BigDecimal decimal) {
        return truncateDelZero(decimal, 2);
    }

    /**
     * decimal末尾去零(未对小数位处理)
     * @param decimal
     * @return
     */
    public static String delZero(BigDecimal decimal) {
        decimal = defVal(decimal);
        return new BigDecimal(decimal.toPlainString()).stripTrailingZeros().toPlainString();
    }

    /**
     * 处理BigDecimal值，null或负值默认返回0
     * */
    public static BigDecimal defVal(BigDecimal val){
        if (val == null || val.compareTo(BigDecimal.ZERO) < 0) {
            return BigDecimal.ZERO;
        }
        return val;
    }

    /**
     * 为String对象赋予默认值
     * @param val 字符串金额
     * @return
     */
    public static BigDecimal defVal(String val){
        if (StringUtils.isBlank(val)) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(val);
    }

    /**
     * 四舍五入保留指定小数位转百分数
     * 0.002555-》0.26%
     * 0.025->2.5%
     * @param decimal 待转百分数
     * @param scale 小数位
     * @return
     */
    public static String percent(BigDecimal decimal, int scale) {
        NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMaximumFractionDigits(scale);
        percent.setRoundingMode(RoundingMode.HALF_UP);
        percent.setGroupingUsed(false);
        if (decimal == null || decimal.compareTo(BigDecimal.ZERO) < 0) {
            return percent.format(BigDecimal.valueOf(0, scale));
        }
        return percent.format(decimal);
    }

    /**
     * 四舍五入保留指定小数位转百分数
     * 0.002555-》0.26%
     * 0.025->2.5%
     * @param decimal 待转百分数
     * @return
     */
    public static String percent(BigDecimal decimal) {
        return percent(decimal, 2);
    }

    /**
     * 数值多条件格式转换
     * @param number             需转换的数值
     * @param isGroup           是否三段式格式，三段式 eg: 2,456,789
     * @param stripTrailingZeros 是否去掉小数位最后多余的零
     * @param roundUp            四舍五入的处理，true：四舍五入，false：直接截掉多余的小数位  null不处理
     * @param digit              保留几位小数
     * @return
     * @Author linxp
     */
    public static String format(Object number, boolean isGroup, boolean stripTrailingZeros, Boolean roundUp, Integer digit) {
        if (number == null) {
            number = BigDecimal.ZERO;
        }
        DecimalFormat df = new DecimalFormat();
        if (roundUp != null) {
            df.setRoundingMode(roundUp ? RoundingMode.HALF_UP : RoundingMode.DOWN);
        }
        if (digit != null) {
            df.setMaximumFractionDigits(digit);
            if (!stripTrailingZeros) {
                df.setMinimumFractionDigits(digit);
            }
        }
        df.setGroupingUsed(isGroup);
        return df.format(number);
    }

    /**
     * 标准金额格式，包括：三段式显示，保留2为小数，截掉小数后多余的位数
     * 1000000.255-> 1,000,000.25
     * @param decimal
     * @return
     */
    public static String moneyFormat(BigDecimal decimal) {
        return format(decimal, true, false, false, 2);
    }

    /**
     * 标准金额格式，包括：三段式显示，保留2为小数，四舍五入
     * 1000000.255-> 1,000,000.26
     * @param decimal
     * @return
     */
    public static String moneyUpFormat(BigDecimal decimal) {
        return format(decimal, true, false, true, 2);
    }

    /**
     * 标准金额格式，包括：三段式显示,四舍五入保留2为小数，去掉小数后多余的零
     * 1000000.204->1000000.2
     * @param decimal
     * @return
     */
    public static String moneyUpFormatNoZero(BigDecimal decimal) {
        return format(decimal, true, true, true, 2);
    }

}
