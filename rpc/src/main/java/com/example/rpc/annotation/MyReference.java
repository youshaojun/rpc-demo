package com.example.rpc.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author yousj
 * @since 2021/1/14
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyReference {
    String value() default "";
}
