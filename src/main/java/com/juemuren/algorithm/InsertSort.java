package com.juemuren.algorithm;

/**
 * Created by 肖文 on 2018/10/13.
 * 插入排序
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] list = {27, 76, 47, 23, 7, 32, 19, 86};
        System.out.println("************直接插入排序************");
        System.out.println("排序前：");
        display(list);
        System.out.println("排序后：");
        insertSort(list);
        display(list);
    }

    private static void insertSort(int[] list) {
        for (int i = 1; i < list.length; i++) {
            int max = list[i];
            int j;
            for (j = i - 1; j >= 0 && list[j] > max; j--) {
                list[j + 1] = list[j];
            }
            list[j + 1] = max;
        }
    }

    private static void display(int[] list) {
        if (list != null && list.length > 0) {
            for (int i : list) {
                System.out.print(" " + i);
            }
        }
    }
}
