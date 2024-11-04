package com.clover.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.clover.service.*.*(..))")
    public void serviceLayerExecution() {}

    @Before("serviceLayerExecution()")
    public void beforeAdvice(JoinPoint joinPoint) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String calledTime = dateFormat.format(new Date());
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Before advice: " + calledTime + " - " + methodName);
    }

    @AfterReturning(pointcut = "serviceLayerExecution()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String calledTime = dateFormat.format(new Date());
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        System.out.println(calledTime + " - " + className + " - " + methodName + " - Returned: " + result);
    }
}