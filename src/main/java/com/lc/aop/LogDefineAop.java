package com.lc.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LogDefineAop {

    @Pointcut("bean(userServerImpl)")  //待增强的对象  ，里面所有所有方法都增强
    public void logPoint(){}

    @Around("logPoint()")
    public Object around(ProceedingJoinPoint jp) throws Throwable{
        log.info("start()..." + System.currentTimeMillis());
        Object proceed = jp.proceed(); //相当于执行了目标对象的方法
        return proceed;
    }

}
