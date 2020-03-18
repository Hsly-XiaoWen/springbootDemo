package com.juemuren.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @Test
    public void testRun() {
        Future<Integer> result = this.taskService.run(() -> {
            Thread.sleep(30);
            System.out.println("=======================");
            return "1";
        });

        Future<Integer> result1 = this.taskService.run(() -> {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            return "2";
        });
    }

    @Test
    public void testRunNotReturnTask() {
        taskService.runNotReturnTask(()-> System.out.println("xiaowen"));
    }
}