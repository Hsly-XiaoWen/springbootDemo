package com.juemuren.algorithm;

/**
 * Created by 肖文 on 2018/9/30
 * 快速排序
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] list = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        System.out.println("************快速排序************");
        System.out.println("排序前：");
        display(list);
        System.out.println("排序后：");
        quickSort(list, 0, list.length - 1);
        display(list);
    }

    public static void quickSort(int[] data, int left, int right) {
        int start = left;
        int end = right;
        int first = data[left];
        while (left < right) {
            while (left < right && data[right] >= first) {
                right--;
            }
            swap(data, left, right);
            while (left < right && data[left] <= first) {
                left++;
            }
            swap(data, left, right);
        }
        System.out.println(left+":"+right);
        if (left > start) {
            quickSort(data, start, left - 1);
        }
        if (right < end) {
            quickSort(data, left + 1, data.length - 1);
        }
    }

    /**
     * 交换数组中两个位置的元素
     */
    public static void swap(int[] list, int left, int right) {
        int temp;
        if (list != null && list.length > 0) {
            temp = list[left];
            list[left] = list[right];
            list[right] = temp;
        }
    }

    /**
     * 遍历打印
     */
    public static void display(int[] list) {
        if (list != null && list.length > 0) {
            for (int num :list) {
                System.out.print(num + " ");
            }
            System.out.println("");
        }
    }
}
