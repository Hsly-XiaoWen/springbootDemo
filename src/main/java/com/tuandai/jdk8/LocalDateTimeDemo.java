package com.tuandai.jdk8;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * Created by 肖文 on 2018/4/24
 * jdk8新增了LocalDateTime来对时间日期处理
 */
public class LocalDateTimeDemo {

    private static final Logger logger = LoggerFactory.getLogger(LocalDateTimeDemo.class);

    /**
     * 获取日期
     */
    @Test
    public void testLocalDate(){
        LocalDate localDate = LocalDate.now();
        logger.info("当前的是日期是{},{}年,{}日,星期{},第{}天",localDate,localDate.getYear(),localDate.getDayOfMonth()
                    ,localDate.getDayOfWeek(),localDate.getDayOfYear());
        int day = localDate.getDayOfMonth();//当前月的第几天
        Month month = localDate.getMonth();//返回月份的英文名 APRIL：四月
        int value = localDate.getMonthValue();//返回int月份，4月
        logger.info("获取到的时间是{},{},{},{}",day,month,value);

        LocalDate date = LocalDate.ofYearDay(2018, 115);
        date.lengthOfMonth();//这个月有多少天
        date.lengthOfYear();//这个年有多少天
//        SimpleDateFormat 线程不安全  DateTimeFormatter是线程安全的，定义为final不能修改
        LocalDate.parse("2018.02.29",DateTimeFormatter.ofPattern("yyyy.MM.dd"));

        /**
         * LocalDate配合TemporalAdjusters进行日期转化
         */
        LocalDate date1 = LocalDate.now().with(TemporalAdjusters.dayOfWeekInMonth(1,DayOfWeek.FRIDAY));
        logger.info("当前月份的第一周的星期五{}",date1);
        LocalDate date2 = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate date3 = LocalDate.now().with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY));
        logger.info("当前月份的最后一个星期五{}，{},{}",date3,date2,LocalDate.now().minusWeeks(0));

        //Date转LocalDate
        Date d = new Date();
        Instant instant = d.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();

        LocalDate date4 = instant.atZone(zoneId).toLocalDate();

        //LocalDate转化为Date
        ZonedDateTime zdt = LocalDate.now().atStartOfDay(zoneId);

        Date date5 = Date.from(zdt.toInstant());
        logger.info("Date与LocalDate之间转化{}，{}",date4,date5);
        String dates=DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDate.now());
        System.out.println(dates);
    }
    /**
     * 获取时间
     * LocalTime相当于String，是final的，不能再修改。当对LocalTime修改时会产生一个新的对象
     */
    @Test
    public void testLocalTime(){
        LocalTime localTime = LocalTime.now();
        int hour=localTime.getHour();//获取小时
        int minute=localTime.getMinute();//获取分钟
        int second=localTime.getSecond();//获取秒
        int nano=localTime.getNano();//毫秒
        logger.info("当前的是日期是{},{},{},{},{}",localTime,hour,minute,second,nano);
        logger.info("当前时间是{},{}",localTime.minusMinutes(5),LocalTime.now());
        localTime.plusHours(3);//在当前时间添加时间
        localTime.plusMinutes(3);//当前时间加3分钟
        boolean result=localTime.minusMinutes(5).isAfter(LocalTime.now());
        boolean result1 = LocalTime.now().isAfter(localTime);
        logger.info("result{},,,{}",result,result1);

        LocalTime localTime1 = LocalTime.now();
        LocalTime time = localTime1.withHour(6);//更改当前时间的小时数
        time=time.minusMinutes(20);
        LocalTime time2 = localTime1.minusHours(1);//当前时间小时数减指定数
        logger.info("执行后的结果是{},{},{}",localTime1,time,time2);

    }

    /**
     * LocalDateTime的测试
     */
    @Test
    public void testLocalDateTime(){
//        LocalDateTime.of()构建时间
        LocalDateTime dateTime=LocalDateTime.of(LocalDate.now(), LocalTime.now());
        LocalDateTime dateTime1=LocalDateTime.of(2018, 5, 12,
                15, 18, 30, 988);
        logger.info("LocalDateTime对象的构建{},{}",dateTime,dateTime1);

        //Date转化为LocalDateTime
        LocalDateTime localDateTime=LocalDateTime.now().ofInstant(new Date().toInstant(), ZoneId.systemDefault());

        //LocalDateTime转Date
        Date date=Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        logger.info("Date与LocalDateTime之间的转化{},{}",localDateTime,date);

        //获取指定的时间格式
        String dateTime2=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒"));
        logger.info("获取指定的时间格式{}",dateTime2);

//        localDateTime.getXXX 获取年月日时分秒等数据
//        localDateTime.withXXX() 修改对应字段对应时间
    }
}
