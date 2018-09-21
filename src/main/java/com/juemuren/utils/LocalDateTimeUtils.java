package com.juemuren.utils;

import com.alibaba.fastjson.JSON;
import com.juemuren.entiy.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * Created by 肖文 on 2018/4/25
 * 时间工具类
 */
public class LocalDateTimeUtils {

    private static final Logger logger = LoggerFactory.getLogger(LocalDateTimeUtils.class);

    /**
     * 整合TemporalAccessor实现转化为String
     * @param accessor
     * @param format
     * @return
     */
    public static String format(TemporalAccessor accessor, String format) {
        if (accessor == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(accessor);
    }

    public static String format(LocalDateTime localDateTime, String format) {
        if (localDateTime == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(localDateTime);
    }

    public static String format(LocalTime localTime, String format) {
        if (localTime == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(localTime);
    }

    /**
     * 测试TemporalAccessor实现转化为String
     */
    @Test
    public void testStringDemo(){
        logger.info("转化后的结果是{}",format(LocalDateTime.now(), "yyyy-MM-dd hh:mm:ss"));
        logger.info("转化后的结果是{}",format(LocalDate.now(), "yyyy-MM-dd"));
        logger.info("转化后的结果是{}",format(LocalTime.now(), "hh:mm:ss"));
        logger.info("转化后的结果是{}", JSON.toJSONString(new User()));
    }

    /**
     * String转化为LocalDateTime
     * @param time
     * @param format 格式
     * @return
     */
    public static LocalDateTime stringToLocalDateTime(String time,String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(time, formatter);
    }

    /**
     * String转化为LocalDateTime
     * @param time
     * @param format 格式
     * @return
     */
    public static LocalDate stringToLocalDate(String time,String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(time, formatter);
    }

    /**
     * String转化为LocalDateTime
     * @param time
     * @param format 格式
     * @return
     */
    public static LocalTime stringToLocalTime(String time,String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalTime.parse(time, formatter);
    }

    /**
     *
     * @param time
     * @param format
     * @return
     */
    public static Date stringToDate(String time, String format) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(time);
    }

    @Test
    public void testStringToLocal() throws Exception{
        logger.info("String转化为Local*{}",stringToLocalDateTime("2018-02-08 08:08:08","yyyy-MM-dd HH:mm:ss"));
        logger.info("String转化为Local*{}",stringToLocalDate("2018/02/08","yyyy/MM/dd"));
        logger.info("String转化为Local*{}",stringToLocalTime("08.08.08","HH.mm.ss"));
        logger.info("String转化为Local*{}",stringToDate("2018-02-08 08:08:08","yyyy-MM-dd HH:mm:ss"));
        logger.info("String转化为Local*{}",stringToDate("2018/02/08 08:08:08","yyyy/MM/dd HH:mm:ss"));
        logger.info("String转化为Local*{}",stringToDate("2018.02.08 08:08:08","yyyy.MM.dd HH:mm:ss"));
    }
    /**
     * LocalDateTime转化为Date
     */
    public static Date getDate(LocalDateTime localDateTime) {
        return localDateTime == null ? null : Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDateTime转化为Date测试
     */
    @Test
    public void testGetDate(){
        logger.info("测试{}",getDate(LocalDateTime.now()));
    }


    /**
     * Date转化为LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime getLocalDateTime(Date date){
        return LocalDateTime.ofInstant(date.toInstant(),ZoneId.systemDefault());
    }

    /**
     * Date转化为LocalDate
     * @param date
     * @return
     */
    public static LocalDate getLocalDate(Date date) {
        LocalDateTime dateTime=getLocalDateTime(date);
        return dateTime.toLocalDate();
    }

    /**
     * date转化为LocalTime
     * @param date
     * @return
     */
    public static LocalTime getLocalTime(Date date) {
        LocalDateTime dateTime=getLocalDateTime(date);
        return dateTime.toLocalTime();
    }

    @Test
    public void testGetLocalTime(){
        logger.info("转为后的日期是{}",LocalDateTimeUtils.getLocalDateTime(new Date()));
        logger.info("转为后的日期是{}",LocalDateTimeUtils.getLocalDate(new Date()));
        logger.info("转为后的日期是{}",LocalDateTimeUtils.getLocalTime(new Date()));
    }
}
