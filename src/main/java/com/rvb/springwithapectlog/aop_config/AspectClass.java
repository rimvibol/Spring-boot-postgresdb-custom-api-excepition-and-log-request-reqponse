package com.rvb.springwithapectlog.aop_config;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rvb.springwithapectlog.model.SysLog;
import com.rvb.springwithapectlog.repository.SysLogRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import org.json.*;


@Aspect
@Component
public class AspectClass {

    @Autowired
    SysLogRepository sysLogRepository;


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
            saveSysLog(httpServletRequest, requestData, objectMapper, proceed);
            return proceed;
        } catch (Exception ex) {
            ex.printStackTrace();
            if (joinPoint.getArgs().length > 0) {
                requestData = joinPoint.getArgs()[0];
            }
            saveSysLog(httpServletRequest, requestData, objectMapper, proceed);
            return proceed;
        }

    }

    private void saveSysLog(HttpServletRequest httpServletRequest, Object requestData, ObjectMapper objectMapper, Object proceed) throws JsonProcessingException {
        SysLog sysLog;
        sysLog = new SysLog();
        sysLog.setRequestData(objectMapper.writeValueAsString(requestData));
        JSONObject jsonObject = new JSONObject(proceed);
        if (jsonObject.length() > 0) {
            sysLog.setResponseData(objectMapper.writeValueAsString(proceed));

        } else {
            sysLog.setResponseData(jsonObject.toString());
        }
        sysLog.setRequestUrl(httpServletRequest.getRequestURI());
        try {
            sysLogRepository.save(sysLog);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
