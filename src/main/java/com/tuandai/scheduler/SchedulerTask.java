package com.tuandai.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 肖文 on 2018/4/19
 */
@Component
public class SchedulerTask {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH-mm-ss");

    /**
     * fixedRate：上一次开始执行时间点之后6秒再执行
     * fixedDelay ：上一次执行完毕时间点之后6秒再执行
     * @Scheduled(initialDelay=1000, fixedRate=6000) ：第一次延迟1秒后执行，之后按fixedRate的规则每6秒执行一次
     */
    @Scheduled(fixedRate = 6000)
    public void reportCurrentTime() {
        System.out.println("现在时间：" + dateFormat.format(new Date()));
    }

    private int count=0;

    /**
     * 表示时隔6秒执行打印
     */
    @Scheduled(cron="*/6 * * * * ?")
    private void process(){
        System.out.println("this is scheduler task runing  "+(count++));
    }
}
