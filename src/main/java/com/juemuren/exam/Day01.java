package com.juemuren.exam;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 华为编程题：https://www.nowcoder.com/ta/huawei
 */
public class Day01 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        sc.close();

        String str = Integer.toBinaryString(a);
        String temp = str.replaceAll("0", "");
        System.out.println(temp.length());
        //test1();

        //test2();

        //test3();

        //test4();

        //test8();
        test10();
    }

    /**
     * 计算字符串最后一个单词的长度，单词以空格隔开。
     */
    public static void test1() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        sc.close();

        System.out.println(str);
        int index = str.lastIndexOf(" ");
        System.out.println(index);
        if (index > 0) {
            System.out.println(str.length() - index - 1);
        }
    }

    /**
     * 写出一个程序，接受一个由字母和数字组成的字符串，和一个字符，
     * 然后输出输入字符串中含有该字符的个数。不区分大小写。
     */
    public static void test2() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String s = sc.next();
        sc.close();

        int count = 0;
        if (str.length() <= 0) {
            System.out.println(count);
            return;
        }

        for (char c : str.toCharArray()) {
            if (s.equalsIgnoreCase(String.valueOf(c))) {
                count++;
            }
        }
        System.out.println(count);
    }

    /**
     * •连续输入字符串，请按长度为8拆分每个字符串后输出到新的字符串数组；
     * •长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
     */
    public static void test3() {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        sc.close();

        method3(s1);
        method3(s2);
    }

    public static void method3(String s1) {
        int size = s1.length() / 8;
        if (size == 0) {
            int length = 8 - s1.length();
            StringBuilder builder = new StringBuilder(s1);
            for (int i = 0; i < length; i++) {
                builder.append("0");
            }
            System.out.println(builder);
        } else {
            for (int i = 0; i <= size; i++) {
                String temp;
                if (i * 8 <= s1.length() - 8) {
                    temp = s1.substring(i * 8, i * 8 + 8);
                    System.out.println(temp);
                } else {
                    temp = s1.substring(i * 8);
                    if (temp.length() == 0) {
                        return;
                    }
                    int length = 8 - temp.length();
                    StringBuilder builder = new StringBuilder(temp);
                    for (int j = 0; j < length; j++) {
                        builder.append("0");
                    }
                    System.out.println(builder);
                }
            }
        }
    }

    /**
     * 十六进制转化为十进制
     */
    public static void test4() {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();//输入十六进制数
        scanner.close();
        int sum = 0;
        for (int i = 2; i < string.length(); i++) {
            int m = string.charAt(i);//将输入的十六进制字符串转化为单个字符
            int num = m >= 'A' ? m - 'A' + 10 : m - '0';//将字符对应的ASCII值转为数值
            sum += Math.pow(16, string.length() - 1 - i) * num;
        }
        System.out.println(sum);
    }

    /**
     * 功能:输入一个正整数，按照从小到大的顺序输出它的所有质因子（如180的质因子为2 2 3 3 5 ）
     */
    public static void test5() {
        Scanner scanner = new Scanner(System.in);
        long l = scanner.nextLong();
        scanner.close();

        StringBuilder builder = new StringBuilder();
        int index = 2;
        while (index <= l) {
            if (l % index == 0) {
                if (index == l) {
                    builder.append(index).append(" ");
                    break;
                } else {
                    builder.append(index).append(" ");
                    l = l / index;
                }
            } else {
                index++;
            }
        }
        System.out.println(builder.toString());
    }

    /**
     * 写出一个程序，接受一个正浮点数值，输出该数值的近似整数值。
     * 如果小数点后数值大于等于5,向上取整；小于5，则向下取整。
     */
    public static void test6() {
        Scanner sc = new Scanner(System.in);
        double d = sc.nextDouble();
        sc.close();
        int i = (int) d;
        System.out.println(d - i >= 0.5 ? i + 1 : i);
    }

    /**
     * 数据表记录包含表索引和数值（int范围的整数），请对表索引相同的记录进行合并，
     * 即将相同索引的数值进行求和运算，输出按照key值升序进行输出。
     */
    public static void test7() {
        Scanner sc = new Scanner(System.in);
        SortedMap<Integer, Integer> map = new TreeMap<>();
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            String[] mid = sc.nextLine().split(" ");
            int key = Integer.parseInt(mid[0]);
            int value = Integer.parseInt(mid[1]);
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + value);
            } else {
                map.put(key, value);
            }
        }
        StringBuilder builder = new StringBuilder();
        for (SortedMap.Entry<Integer, Integer> e : map.entrySet()) {
            builder.append(e.getKey()).append(" ").append(e.getValue()).append("\r");
        }
        System.out.println(builder.toString());
    }

    /**
     * 输入一个int型整数，按照从右向左的阅读顺序，返回一个不含重复数字的新的整数。
     */
    public static void test8() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        sc.close();
        List<Character> set = new ArrayList<>();
        char[] strs = str.toCharArray();
        for (int i = strs.length - 1; i >= 0; i--) {
            char c = strs[i];
            if (!set.contains(c)) {
                set.add(strs[i]);
            }
        }

        StringBuilder builder = new StringBuilder();
        set.forEach(builder::append);
        System.out.println(builder.toString());
    }

    /**
     * 编写一个函数，计算字符串中含有的不同字符的个数。
     * 字符在ACSII码范围内(0~127)，换行表示结束符
     * 不算在字符里。不在范围内的不作统计。
     */
    public static void test9() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        sc.close();
        char[] strs = str.toCharArray();
        StringBuilder build = new StringBuilder();
        for (int i = strs.length - 1; i >= 0; i--) {
            build.append(strs[i]);
        }
        System.out.println(build.toString());
    }

    /**
     * 给定n个字符串，请对n个字符串按照字典序排列。
     */
    public static void test10() {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();

        List<String> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(sc.next());
        }

        List<String> temp = list.stream().sorted().collect(Collectors.toList());
        temp.forEach(System.out::println);

    }
}
