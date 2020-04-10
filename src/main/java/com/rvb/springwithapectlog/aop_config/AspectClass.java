package com.rvb.springwithapectlog.aop_config;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rvb.springwithapectlog.model.SysLog;
import com.rvb.springwithapectlog.repository.SysLogRepository;
import com.rvb.springwithapectlog.service.SysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import org.json.*;


@Aspect
@Component
public class AspectClass {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysLogService logService;

    @Around(value = "@annotation(EnableSaveLog)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        Object requestData = null;
        SysLog sysLog = null;
        ObjectMapper objectMapper = new ObjectMapper();
        Object proceed = new Object();

        try {

            proceed = joinPoint.proceed();
            if (joinPoint.getArgs().length > 0) {
                requestData = joinPoint.getArgs()[0];
            }
            log.info("AOP processing to save log in to database with {} and service name: {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName());
            logService.saveLog(httpServletRequest, requestData, objectMapper, proceed);
            return proceed;
        } catch (Exception ex) {

            if (joinPoint.getArgs().length > 0) {
                requestData = joinPoint.getArgs()[0];
            }
            log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), ex.getCause() != null? ex.getCause() : "NULL");
            logService.saveLog(httpServletRequest, requestData, objectMapper, proceed);
            return proceed;
        }

    }


}
