package com.lc.utils;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice  //全局异常处理
public class GloableIExceptionHandler {
    @ExceptionHandler //当发生AuthorizationException异常返回lessAuthtication.html界面
    public String doException(Exception e) {
        if (e instanceof AuthorizationException) {
            return "lessAuthtication";
        }else {
            return null;
        }
    }
}
