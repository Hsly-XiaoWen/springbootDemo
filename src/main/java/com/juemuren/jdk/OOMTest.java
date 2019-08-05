package com.juemuren.jdk;

import javassist.ClassPool;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class OOMTest {

    static final int SIZE = 2 * 1024 * 1024;

    /**
     * 以下代码非常简单, 程序试图分配容量为 2M 的 int 数组. 如果指定启动参数 -Xmx12m,
     * 那么就会发生 java.lang.OutOfMemoryError: Java heap space 错误。
     * 而只要将参数稍微修改一下, 变成 -Xmx13m, 错误就不再发生。
     * 开始的
     */
    @Test
    public void oomTest() {
        int[] i = new int[SIZE];
    }

    /**
     * 不断往堆内存存放数据，最终将会存满堆内存。
     */
    @Test
    public void oomTest1() {
        List<OOMTest> list = new ArrayList<>();
        while (true) {
            list.add(new OOMTest());
        }
    }

    /**
     * 不断生class，一直往永生代存放数据，将导致metespace内存溢出
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100_000_000; i++) {
            generate("eu.plumbr.demo.Generated" + i);
        }
    }

    public static Class generate(String name) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        return pool.makeClass(name).toClass();
    }
}
