package com.juemuren.config;

import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
@Slf4j
@Async
public class TaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskService.class);

    /**
     * 运行带返回值任务
     * @param <T>
     * @return
     */
    public <T> Future<T> run(RunTask task) {
        try {
            System.out.println(Thread.currentThread().getName() + "============");
            return new AsyncResult(task.run());
        } catch (Exception e) {
            log.error(String.format("运行无返回任务错误:%s", e.getMessage()), e);
        }
        return null;
    }

    /**
     * 运行无返回任务
     * @param task 任务
     */
    public void runNotReturnTask(RunNotReturnTask task){
        try {
            System.out.println(Thread.currentThread().getName() + "============");
            task.run();
        } catch (Exception e) {
            log.error(String.format("运行无返回任务错误:%s",e.getMessage()),e);
        }
    }

}
