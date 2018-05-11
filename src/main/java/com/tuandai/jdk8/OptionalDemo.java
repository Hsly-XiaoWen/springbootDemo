package com.tuandai.jdk8;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Created by 肖文 on 2018/5/11
 * Optional 类是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。
 * Optional 是个容器：它可以保存类型T的值，或者仅仅保存null。Optional提供很多有用的方法，这样我们就不用显式进行空值检测。
 * Optional 类的引入很好的解决空指针异常。
 */
public class OptionalDemo {

    private static final Logger logger = LoggerFactory.getLogger(OptionalDemo.class);

    @Test
    public void optional(){
        Integer value=null;
        Integer result = Optional.ofNullable(value).orElse(new Integer(3));
        /**
         * 如果值存在则方法会返回true，否则返回 false。
         */
        boolean result1 = Optional.ofNullable(value).isPresent();
        logger.info("返回的结果是{},{}",result,result1);
    }
}
