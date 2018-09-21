package com.juemuren.utils;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by 肖文 on 2018/4/25
 * BigDecimal帮助类
 */
public class BigDecimalUtils extends BaseUtils{

    public BigDecimalUtils() {
    }

    /**
     * BigDecimal.setScale()方法用于格式化小数点
     * BigDecimal.ROUND_DOWN：直接删除多余的小数 eg:2.35->2.3
     * BigDecimal.ROUND_UP：进位处理 eg:2.35->2.4
     * ROUND_FLOOR
     * BigDecimal.ROUND_HALF_UP：四舍五入 eg：2.35->2.4
     * BigDecimal.ROUND_HALF_DOWN：四舍五入,向下舍弃 eg：2.35->2.3
     * ROUND_HALF_EVEN
     * ROUND_UNNECESSARY
     * @param bigDecimal
     * @param scale
     * @return
     */
    public BigDecimal cutBigDecimal(BigDecimal bigDecimal, int scale,int mode) {
        bigDecimal.intValue();//转化为int
        bigDecimal.doubleValue();//转化为double
        bigDecimal.floatValue();//转化为float
        return bigDecimal.setScale(scale,mode);
    }
    @Test
    public void testRoundingMode(){
        BigDecimal decimal = new BigDecimal(2.35);
        BigDecimal result = cutBigDecimal(new BigDecimal(2.35), 1, BigDecimal.ROUND_DOWN);
        logger.info("结果是{}",result);
        BigDecimal result1 = cutBigDecimal(new BigDecimal(2.35), 1, BigDecimal.ROUND_UP);
        logger.info("结果是{}",result1);
        BigDecimal result2= cutBigDecimal(new BigDecimal(2.355), 2, BigDecimal.ROUND_HALF_DOWN);
        logger.info("结果是{}",result2);
        BigDecimal result3= cutBigDecimal(new BigDecimal(2.355), 2, BigDecimal.ROUND_HALF_UP);
        logger.info("结果是{}",result3);
        BigDecimal result4 = cutBigDecimal(new BigDecimal(2.35), 1, BigDecimal.ROUND_HALF_EVEN);
        logger.info("结果是{}",result4);
        BigDecimal result5 = cutBigDecimal(new BigDecimal(2.5), 0, BigDecimal.ROUND_DOWN);
        logger.info("result5结果是{}",result5);
        BigDecimal result6 = cutBigDecimal(new BigDecimal(2.5), 0, BigDecimal.ROUND_UP);
        logger.info("result6结果是{}",result6);
        BigDecimal result7= cutBigDecimal(new BigDecimal(2.5), 0, BigDecimal.ROUND_HALF_DOWN);
        logger.info("result7结果是{}",result7);
        BigDecimal result8= cutBigDecimal(new BigDecimal(2.5), 0, BigDecimal.ROUND_HALF_UP);
        logger.info("result8结果是{}",result8);
        BigDecimal result9 = cutBigDecimal(new BigDecimal(2.5), 0, BigDecimal.ROUND_HALF_EVEN);
        logger.info("result9结果是{}",result9);
    }


    @Test
    public void test() {
        LocalDate date1 = LocalDate.of(2018, 11, 30);
        LocalDate date2 = LocalDate.of(2018, 10, 31);
        long days = date1.toEpochDay() - date2.toEpochDay();
        System.out.println(days);
    }
}
