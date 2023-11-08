package com.hspedu.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 韩顺平
 * @version 1.0
 * Scope 可以指定Bean的作用范围[singleton, prototype]
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Scope {
    //通过value可以指定singleton,prototype
    String value() default "";
}
