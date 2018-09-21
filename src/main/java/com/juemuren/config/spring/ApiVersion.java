package com.juemuren.config.spring;

import java.lang.annotation.*;

/**
 * Created by 肖文 on 2018/7/10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface ApiVersion {

    Version version() default Version.V_1_1_0;
}
