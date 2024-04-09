package supermarket.manage.system.common.exception.handler;

//全局异常处理


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import supermarket.manage.system.common.commons.AppResult;
import supermarket.manage.system.common.commons.enumeration.ResultCode;
import supermarket.manage.system.common.exception.ApplicationException;

@Slf4j
@RestControllerAdvice   //控制器通知类
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public AppResult applicationException(ApplicationException e){
        // 打印异常信息
        e.printStackTrace(); // 上生产之前要删除
        // 打印日志
        log.error(e.getMessage());
        if (e.getErrorResult() != null) {
            return e.getErrorResult();
        }
        // 非空校验
        if (e.getMessage() == null || e.getMessage().equals("")) {
            return AppResult.failed(ResultCode.ERROR_SERVICES);
        }
        // 返回具体的异常信息
        return AppResult.failed(e.getMessage());

    }


    @ExceptionHandler(Exception.class)
    public AppResult exceptionHandler (Exception e) {
        // 打印异常信息
        e.printStackTrace();
        // 打印日志
        log.error(e.getMessage());
        // 非空校验
        if (e.getMessage() == null || e.getMessage().equals("")) {
            return AppResult.failed(ResultCode.ERROR_SERVICES);
        }
        // 返回异常信息
        return AppResult.failed(e.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    public AppResult throwableHandler(Throwable e){
        // 打印异常信息
        e.printStackTrace();
        // 打印日志
        log.error(e.getMessage());
        // 非空校验
        if (e.getMessage() == null || e.getMessage().equals("")) {
            return AppResult.failed(ResultCode.ERROR_SERVICES);
        }
        // 返回异常信息
        return AppResult.failed(e.getMessage());
    }


}
