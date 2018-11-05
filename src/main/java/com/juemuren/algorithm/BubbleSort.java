package com.juemuren.algorithm;

/**
 * Created by 肖文 on 2018/10/13.
 * 冒泡排序算法：相邻的数据之间比较，大的往后挪，一趟下来，最大的数再最后面
 * 复杂度分析：平均情况与最坏情况均为 O(n^2), 使用了 temp 作为临时交换变量，空间复杂度为 O(1).
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] list = {27, 76, 47, 23, 7, 32, 19, 86};
        System.out.println("************冒泡排序************");
        System.out.println("排序前：");
        display(list);
        System.out.println("排序后：");
        bubbleSort(list);
        display(list);
    }

    private static void bubbleSort(int[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            for (int j = 1; j < list.length - i - 1; j++) {
                if (list[j] < list[j - 1]) {
                    int temp = list[j];
                    list[j] = list[j - 1];
                    list[j - 1] = temp;
                }
            }
        }
    }

    /**
     * 遍历打印
     */
    public static void display(int[] list) {
        if (list != null && list.length > 0) {
            for (int num :
                    list) {
                System.out.print(num + " ");
            }
            System.out.println("");
        }
    }
}
