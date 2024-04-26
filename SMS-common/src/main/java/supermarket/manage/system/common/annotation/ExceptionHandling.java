package supermarket.manage.system.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 异常处理注解
 * @author：dukelewis
 * @date: 2024/4/9
 * @Copyright： https://github.com/DukeLewis
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Deprecated
public @interface ExceptionHandling {

    /**
     * 异常信息
     * @return
     */
    String value() default "";

}
