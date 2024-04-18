package supermarket.manage.system.common.aspect;

import com.alibaba.fastjson2.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import supermarket.manage.system.common.annotation.RateLimiter;
import supermarket.manage.system.common.service.IRateLimiterService;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @description: 限流切面
 * @author：dukelewis
 * @date: 2024/4/18
 * @Copyright： https://github.com/DukeLewis
 */
@Component
@Aspect
public class RateLimiterAspect {


    @Resource
    private IRateLimiterService rateLimiterService;


    @Around("@annotation(rateLimiter)")
    public Object rateLimiter(ProceedingJoinPoint pjq, RateLimiter rateLimiter){
        String key = rateLimiter.value();
        long limit = Long.parseLong(rateLimiter.limit());
        String msg = rateLimiter.msg();
        int windowSize = Integer.parseInt(rateLimiter.windowSize());

        Method method = ((MethodSignature) pjq.getSignature()).getMethod();

        boolean res= rateLimiterService.isAllowed(key, limit, windowSize);
        if(res){
            try {
                return pjq.proceed();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        //返回错误信息，对应方法返回值类型的错误信息
        return JSON.parseObject(msg,method.getReturnType());
    }

}
