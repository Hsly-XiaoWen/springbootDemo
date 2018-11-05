package com.juemuren.jdk.thread;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * Created by 肖文 on 2018/10/13
 * CountDownLatch能够使一个线程在等待另外一些线程完成各自工作之后，再继续执行
 * 计数器初始值为线程的数量。当每一个线程完成自己任务后，计数器的值就会减一。
 * 当计数器的值为0时，表示所有的线程都已经完成了任务，然后在CountDownLatch上等待的线程就可以恢复执行任务。
 * 应用场景：当主程序需要在等待其他几个线程执行完成时再继续执行
 */
public class CountDownLatchDemo {

    /**
     * 设置2个线程，主线程在两个线程执行完之后才能继续执行
     *
     * @throws Exception
     */
    @Test
    public void countDownLatch() throws Exception {
        CountDownLatch latch = new CountDownLatch(2);

        new Thread(() -> run(latch)).start();
        new Thread(() -> run(latch)).start();

        try {
            System.out.println("等待2个子线程执行完毕...");
            latch.await();
            System.out.println("2个子线程已经执行完毕");
            System.out.println("继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run(CountDownLatch latch) {
        try {
            System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
            Thread.sleep(3000);
            System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
