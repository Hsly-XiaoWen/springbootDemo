package com.juemuren.utils;

import com.juemuren.utils.functions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by 肖文 on 2018/5/17
 */
public class CollectionUtils {

    private static Logger logger = LoggerFactory.getLogger(Collection.class);

    /**
     * 对集合中元素进行特定的处理
     *
     * @param collection 集合
     * @param handler    实现特定处理的方法
     * @param <T>        泛型
     */
    public static <T> void handler( Collection<T> collection, ObjectHandler<T> handler) {
        if(collection == null || collection.size() == 0){
            logger.error("collection is empty or is null");
            return;
        }
        for (T t : collection) {
            handler.handler(t);
        }
    }

    /**
     * 对集合中的元素进行特定的处理，并获得处理后的结果
     * @param collection 待处理的集合
     * @param result 接受处理后结果的集合
     * @param process 实现的特定处理
     * @param <T>泛型
     * @param <E>泛型
     */
    public static <T,E> void process(Collection<T> collection,  Collection<E> result, ObjectPrecess<T, E> process) {
        if(collection == null || collection.size() == 0){
            logger.error("collection is empty or is null");
            return;
        }
        if(result == null || result.size() == 0){
            logger.error("receive collection is empty or is null");
            return;
        }
        for (T t : collection) {
            E next = process.process(t);
            if (next != null) {
                result.add(next);
            }
        }
    }

    /**
     * 删除集合重复元素
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<T> removeElements(List<T> list) {
        if (list == null || list.isEmpty()) {
            logger.info("list is null or is empty");
            return new ArrayList<T>();
        }
        return new ArrayList<T>(new HashSet<T>(list));
    }

    /**
     * 两个集合取交集
     * @param list1
     * @param list2
     * @param <T>
     * @return
     */
    public static <T> List<T> intersection(List<T> list1, List<T> list2) {
        if (CheckUtils.validCollection(list1,list2)) {
            Set<T> set = new HashSet<T>(list1);
            set.retainAll(list2);
            return new ArrayList<T>(set);
        }
        return new ArrayList<T>();
    }

    /**
     * 求队列的交集
     *
     * @param queue1 队列
     * @param queue2 队列
     * @param <T>    泛型
     * @return 交集
     */
    public static <T> Queue<T> intersection(Queue<T> queue1, Queue<T> queue2) {
        if (CheckUtils.validCollection(queue1, queue2)) {
            Set<T> set = new HashSet<>(queue1);
            set.retainAll(queue2);
            return new LinkedList<>(set);
        }
        return new LinkedList<>();
    }

    /**
     * 取两个集合的并集
     * @param list1
     * @param list2
     * @param <T>
     * @return
     */
    public static<T> List<T> union(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<T>();
        list.addAll(list1);
        list.addAll(list2);
        return list;
    }

    /**
     * Set集合并集
     * @param set1
     * @param set2
     * @param <T>
     * @return
     */
    public static <T> Collection<T> union(Collection<T> set1, Collection<T> set2) {
        set1.addAll(set2);
        return set1;
    }


    /**
     * 取集合的差集
     * @param collection1
     * @param collection2
     * @param <T>
     * @return
     */
    public static <T> Collection<T> subtract(Collection<T> collection1, Collection<T> collection2) {
        if (!CheckUtils.validCollection(collection1,collection2)) {
            return null;
        }
        collection1.removeAll(collection2);
        return collection1;
    }

    /**
     * 将List以separator链接并以字符串的形式返回
     *
     * @param collection
     * @param <T>
     * @return
     */
    public static <T> String join(Collection<T> collection, String separator) {
        StringBuilder stringBuilder = new StringBuilder();
        collection.forEach(x->stringBuilder.append(x.toString()).append(separator));
        return stringBuilder.substring(0, stringBuilder.length()-separator.length());
    }

    /**
     * 将List以separator链接并以字符串的形式返回
     *
     * @param collection
     * @param <T>
     * @return
     */
    public static <T> String join(Collection<T> collection, String separator,UserHandler<T> handler) {
        if (!CheckUtils.validCollection(collection)) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        collection.forEach(x->stringBuilder.append(handler.handler(x)).append(separator));
        return stringBuilder.substring(0, stringBuilder.length()-separator.length());
    }

    /**
     * 将Map以separator链接并以字符串的形式返回
     * @param map
     * @param separator
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K,V> String join(Map<K,V> map, String separator,String separator1) {
        if (!CheckUtils.validMap(map)) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        map.forEach((k,v)->stringBuilder.append(k).append(separator)
                .append(v).append(separator1));
        return stringBuilder.substring(0, stringBuilder.length()-separator1.length());
    }

    /**
     * 将Map的value以separator链接并以字符串的形式返回
     * @param map
     * @param separator
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K,V> String valueJoin(Map<K,V> map, String separator, ObjectProcess<V> handler) {
        if (!CheckUtils.validMap(map)) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        map.forEach((k,v)->stringBuilder.append(handler.handler(v)).append(separator));
        return stringBuilder.substring(0, stringBuilder.length()-separator.length());
    }

    /**
     * 将Map的value以separator链接并以字符串的形式返回
     * @param map
     * @param separator
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K,V> String keyJoin(Map<K,V> map, String separator, ObjectProcess<K> handler) {
        if (!CheckUtils.validMap(map)) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        map.forEach((k,v)->stringBuilder.append(handler.handler(k)).append(separator));
        return stringBuilder.substring(0, stringBuilder.length()-separator.length());
    }
}
