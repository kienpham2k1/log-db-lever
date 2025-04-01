package com.example.logdblever.config;

import lombok.extern.log4j.Log4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
//@Log4j
public class LoggingAspect {
    //Ghi log cho duy nhất server
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

//    @Around("execution(* com.example.logdblever.service.*.*(..))")
//    public Object logServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
//        long start = System.currentTimeMillis();
//        logger.info(">>> Start {} with args: {}", joinPoint.getSignature(), joinPoint.getArgs());
//        Object result = joinPoint.proceed();
//        long elapsedTime = System.currentTimeMillis() - start;
//        logger.info("<<< End: {} | Time: {} ms | Result: {}", joinPoint.getSignature(), elapsedTime, result);
//        return result;
//    }

    // Áp dụng cho tất cả method trong Controller, Service, Repository
    @Around("execution(* com.example.logdblever..*(..))")
    public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        String layer = getLayer(className);
        long start = System.currentTimeMillis();
        logger.info("➡️ [{}] Start: {}.{}() | Args: {}", layer, className, methodName, joinPoint.getArgs());

        Object result = joinPoint.proceed();

        long elapsedTime = System.currentTimeMillis() - start;
        logger.info("⬅️ [{}] End: {}.{}() | Time: {} ms | Result: {}", layer, className, methodName, elapsedTime, result);

        return result;
    }

    private String getLayer(String className) {
        if (className.contains("controller")) return "CONTROLLER";
        if (className.contains("service")) return "SERVICE";
        if (className.contains("repository")) return "REPOSITORY";
        return "UNKNOWN";
    }

}
