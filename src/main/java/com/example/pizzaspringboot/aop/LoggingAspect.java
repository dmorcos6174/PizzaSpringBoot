package com.example.pizzaspringboot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

//    @Before(value = "execution(* com.example.pizzaspringboot.controller.*.*(..))")
//    public void logStatementBefore(JoinPoint joinPoint) {
//        logger.info("Started Executing {}", joinPoint);
//    }
//
//    @After(value = "execution(* com.example.pizzaspringboot.controller.*.*(..))")
//    public void logStatementAfter(JoinPoint joinPoint){
//        logger.info("Completed Executing {}", joinPoint);
//    }
//
//    @Around(value = "execution(* com.example.pizzaspringboot.controller.*.*(..))")
//    public void logStatementAround(JoinPoint joinPoint){
//        logger.info("Executing {}", joinPoint);
//    }

    @Around(value = "execution(* com.example.pizzaspringboot.controller.*.*(..))")
    public Object logStatementAround(ProceedingJoinPoint point) throws Throwable {
        logger.info("Before Executing {}", point);
        var result = point.proceed();
        logger.info("After Executing {}", point);
        return result;
    }



}
