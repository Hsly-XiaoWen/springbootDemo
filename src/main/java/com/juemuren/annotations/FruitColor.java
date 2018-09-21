package com.juemuren.annotations;

import java.lang.annotation.*;

/**
 * Created by 肖文 on 2018/7/10
 * ElementType.METHOD作用于方法
 * ElementType.FIELD作用于属性
 * ElementType.TYPE作用于类、接口、枚举
 *
 */
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {
    String value() default "RED";

    String name() default "";

    String address() default "";
}
