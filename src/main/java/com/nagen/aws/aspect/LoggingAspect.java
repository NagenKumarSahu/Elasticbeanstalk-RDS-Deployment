package com.nagen.aws.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {
    @Around("execution(* com.nagen.aws.service.*.*(..))")
    public Object logMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("Execution starts {}.{} with arguments: {}", joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName(), joinPoint.getArgs());

        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;

        log.info("Execution completed {}.{} in {} ms. Return Value: {}", joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName(), executionTime, result);
        return result;
    }
}
