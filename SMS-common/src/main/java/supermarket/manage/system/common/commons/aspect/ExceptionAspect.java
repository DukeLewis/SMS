package supermarket.manage.system.common.commons.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import supermarket.manage.system.common.commons.AppResult;
import supermarket.manage.system.common.commons.annotation.ExceptionHandling;
import supermarket.manage.system.common.exception.ApplicationException;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/4/9
 * @Copyright： https://github.com/DukeLewis
 */
@Aspect
@Slf4j
@Deprecated
public class ExceptionAspect {

    @Around("@annotation(exceptionHandling)")
    public Object exceptionHandle(ProceedingJoinPoint joinPoint, ExceptionHandling exceptionHandling) {
        log.info("进入异常处理切面");
        String msg = exceptionHandling.value();
        Object ret = null;
        try {
            ret = joinPoint.proceed();
        } catch (ApplicationException e) {
            log.info("ApplicationException异常信息：{}", e.getMessage());
            throw new ApplicationException(AppResult.failed(e.getMessage()));
        } catch (Exception e) {
            log.error("Exception异常信息：{}", e.getMessage());
            throw new ApplicationException(AppResult.failed(msg));
        } catch (Throwable e) {
            log.error("Throwable异常信息：{}", e.getMessage());
            throw new ApplicationException(e);
        }
        return ret;
    }
}
