package com.juemuren.annotations;

import java.lang.annotation.*;

/**
 * Created by 肖文 on 2019/1/3.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BrushRedis {

    String key();
    long timeOut() default 3 * 1000L;
    String value();
}
