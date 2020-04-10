package com.rvb.springwithapectlog.aop_config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Vibol rim
 */
@Aspect
@Component
public class AopControllerLog {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Pointcut("within(com.rvb.springwithapectlog.controller..*)" +
            "|| within(com.rvb.springwithapectlog.repository..*)" +
            "|| within(com.rvb.springwithapectlog.service..*)")
    public void applicationPackagePointcut() {
    }

    @AfterThrowing(pointcut = "applicationPackagePointcut() && applicationPackagePointcut()", throwing = "e")
    public void logAfterThrowingException(JoinPoint joinPoint, Throwable e) {
        log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");

    }


//    @Before(p = "applicationPackagePointcut() && applicationPackagePointcut()", throwing = "e")
//    public void before(JoinPoint joinPoint, Throwable e) {
//        log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
//                joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
//
//    }
}
