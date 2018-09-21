package com.juemuren.jdk8;

import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.util.Date;

/**
 * Created by 肖文 on 2018/6/29
 * Clock类提供了访问当前日期和时间的方法，Clock是时区敏感的，可
 * 以用来取代 System.currentTimeMillis() 来获取当前的微秒数。
 * 某一个特定的时间点也可以使用Instant类来表示，
 * Instant类也可以用来创建老的java.util.Date对象。
 */
public class ClockDemo {

    @Test
    public void test() {
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        System.out.println(millis);
        Instant instant = clock.instant();
        Date legacyDate = Date.from(instant);
        System.out.println(legacyDate);
    }
}
