package com.tuandai.code;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by 肖文 on 2018/7/24
 */
public class StringDemo {

    @Test
    public void test() {
        char[] names = {'a', 'b', 'd', 'e', 'c', 'f', 'g'};
        String result = new String(names, 2, 5);
        System.out.println(result);
        char[] c = copyOfRange(names, 2, 7);
        System.out.println(c);
        Arrays.sort(names,2,5);
        System.out.println(names);
        List<String> list = Arrays.asList("a", "b", "c");
        list.forEach(x->System.out.println(x));
        String a = "xiaowen";
        String b = new String("xiaowen");
        boolean d = Objects.equals(a, b);
        System.out.println(d+":"+(a==b));
    }

    public char[] copyOfRange(char[] original, int from, int to) {
        int newLength = to - from;
        if (newLength < 0)
            throw new IllegalArgumentException(from + " > " + to);
        char[] copy = new char[newLength];
        System.arraycopy(original, from, copy, 0,
                Math.min(original.length - from, newLength));
        return copy;
    }
}
