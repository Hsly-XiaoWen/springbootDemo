package com.juemuren.exam;

import java.util.Scanner;

/**
 * 华为编程题：https://www.nowcoder.com/ta/huawei
 */
public class Day02 {

    public static void main(String[] args) {

    }

    /**
     * 求最大公约数
     * 如果m > n，令余数remainder = m%n，如果余数不为0，
     * 则令m = n， n = remainder，再次remainder = m%n，直到remainder = 0，此时n就是最大公约数。
     */
    public static void test() {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        sc.close();
        if (a == 0 || b == 0) {
            System.out.println(0);
        }
        int m = a;
        int n = b;
        if (a < b) {
            m = b;
            n = a;
        }

        while (m % n != 0) {
            int temp = m % n;
            m = n;
            n = temp;
        }
        //n为最大公约数，两个数相乘等于这两个数的最大公约数和最小公倍数的积。
        System.out.println(a * b / n);
    }
}
