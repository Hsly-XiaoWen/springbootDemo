package com.juemuren.jdk.thread;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by 肖文 on 2018/10/15.
 */
public class CyclicBarrierDemo {

    private static Logger logger = LoggerFactory.getLogger(CyclicBarrierDemo.class);

    @Test
    public void cyclicBarrier() {
        CyclicBarrier barrier = new CyclicBarrier(4);
        System.out.println("主线程执行中。。。。。。");
        for (int i = 0; i < 4; i++) {
            new ThreadDemo(barrier).start();
        }

    }

    static class ThreadDemo extends Thread {

        CyclicBarrier barrier;
        public ThreadDemo(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            System.out.println("线程" + Thread.currentThread().getName() + "正在写入数据...");
            try {
                Thread.sleep(5000);
                System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕");
                barrier.await();
            } catch (Exception e) {
                logger.info("发生异常{}", e.getMessage());
            }
            System.out.println(Thread.currentThread().getName() + "所有线程写入完毕，继续处理其他任务...");
        }
    }
}
