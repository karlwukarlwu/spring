package com.hspedu.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 韩顺平
 * @version 1.0
 */
//这个是给指定扫描的包下的类，加上@Component注解
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {
    //通过value可以给注入的bean/对象指定名字
    String value() default "";
}
