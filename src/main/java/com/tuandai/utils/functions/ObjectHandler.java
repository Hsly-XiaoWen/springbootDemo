package com.tuandai.utils.functions;

/**
 * Created by 肖文 on 2018/5/17
 */
@FunctionalInterface
public interface ObjectHandler<T> {
    void handler(T t);
}
