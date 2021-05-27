package com.chen.gym.exception;

import com.chen.gym.security.CustomRealm;
import com.chen.gym.util.JsonResult;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

/**
 * @author CHEN
 * @date 2020/10/13  18:28
 */
//@RestControllerAdvice
public class MyCustomizeExceptionHandler {

    @ExceptionHandler(CustomRealm.CustomizeAuthenticationException.class)
    public JsonResult CustomizeAuthenticationException(CustomRealm.CustomizeAuthenticationException ex) {
        return JsonResult.errorOf(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(CustomizeRuntimeException.class)
    public JsonResult CustomizeRuntimeException(CustomizeRuntimeException ex) {
        return JsonResult.errorOf(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(MultipartException.class)
    public JsonResult handleMultipartException() {
        return JsonResult.errorOf(MyCustomizeErrorCode.FILE_MAX_SIZE_EXCEPTION);
    }

    @ExceptionHandler(RuntimeException.class)
    public JsonResult handleCustomizeException(HttpServletRequest request, Throwable ex) {
        //获取错误状态码
        HttpStatus status = getStatus(request);
        if (status.is5xxServerError() || ex instanceof ParseException) {
            return JsonResult.errorOf(MyCustomizeErrorCode.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
