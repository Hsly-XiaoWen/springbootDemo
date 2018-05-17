package com.tuandai.utils;

import com.tuandai.utils.functions.ObjectHandler;
import com.tuandai.utils.functions.ObjectPrecess;
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
}
