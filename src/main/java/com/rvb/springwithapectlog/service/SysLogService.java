package com.rvb.springwithapectlog.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rvb.springwithapectlog.model.SysLog;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Vibol rim
 */
public interface SysLogService {

     SysLog saveLog(HttpServletRequest httpServletRequest, Object requestData, ObjectMapper objectMapper, Object proceed)throws JsonProcessingException;
}
