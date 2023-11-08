package anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Karl Rules!
 * 2023/11/2
 * now File Encoding is UTF-8
 */
@Target(ElementType.TYPE)
//这个target是用来描述注解的使用范围的
//Class, interface (including annotation type), or enum declaration
@Retention(RetentionPolicy.RUNTIME)
//这个retention是用来描述注解的保留范围
public @interface ComponentScan {
    //这个value是给以后@注解“(value= “”)赋值留的
    String value() default "";
}
