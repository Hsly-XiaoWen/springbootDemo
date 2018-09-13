package com.tuandai.annotations;

import java.lang.annotation.*;

/**
 * Created by 肖文 on 2018/9/13
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String value() default "";
}
