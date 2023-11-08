package anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Karl Rules!
 * 2023/11/4
 * now File Encoding is UTF-8
 */
//这个注解用来说明我们需要扫描的目标文件夹，给配置文件使用的
@Target(ElementType.TYPE)//作用范围
@Retention(RetentionPolicy.RUNTIME)//保留范围
public @interface ComponentScan {
    String value() default "";
}
