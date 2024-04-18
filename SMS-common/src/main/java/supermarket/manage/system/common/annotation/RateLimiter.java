package supermarket.manage.system.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @description: 限流时间单位为分钟级别
 * @author：dukelewis
 * @date: 2024/4/18
 * @Copyright： https://github.com/DukeLewis
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RateLimiter {

    /**
     * 限流key
     * @return
     */
    String value();

    /**
     * 限流数量
     * @return
     */
    String limit();

    /**
     * 限流时间
     * @return
     */
    String windowSize();


    /**
     * 拦截后的提示信息
     * @return
     */
    String msg() default "请求过于频繁，请稍后再试";
}
