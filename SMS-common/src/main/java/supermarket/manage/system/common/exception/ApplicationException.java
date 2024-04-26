package supermarket.manage.system.common.exception;


import supermarket.manage.system.common.commons.AppResult;

//自定义异常信息
public class ApplicationException extends RuntimeException{
    protected AppResult errorResult;


    public ApplicationException(AppResult errorResult){
        super(errorResult.getMessage());
        this.errorResult = errorResult;
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }

    public AppResult getErrorResult() {
        return errorResult;
    }
}
