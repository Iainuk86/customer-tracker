package org.iainuk.tracker.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.mapping.Join;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class CRMLoggingAspect {

    @Pointcut("execution(* org.iainuk.tracker.controller.*.*(..))")
    private void forControllerPackage() {}

    @Pointcut("execution(* org.iainuk.tracker.controller.*.*(..))")
    private void forServicePackage() {}

    @Pointcut("execution(* org.iainuk.tracker.controller.*.*(..))")
    private void forDAOPackage() {}

    @Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
    private void forAppFlow() {}

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {

        String method = joinPoint.getSignature().toShortString();
        log.info("----> Before calling method: " + method);

        Object[] args = joinPoint.getArgs();
        for (Object o : args) {
            log.info("----> Argument: " + o);
        }
    }

    @AfterReturning(
            pointcut="forAppFlow()",
            returning="result"
            )
    public void afterReturning(JoinPoint joinPoint, Object result) {

        String method = joinPoint.getSignature().toShortString();
        log.info("----> After returning from method: " + method);

        log.info("----> Returned object: " + result);
    }
}
