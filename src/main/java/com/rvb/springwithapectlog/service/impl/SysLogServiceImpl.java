package com.rvb.springwithapectlog.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rvb.springwithapectlog.model.SysLog;
import com.rvb.springwithapectlog.repository.SysLogRepository;
import com.rvb.springwithapectlog.service.SysLogService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

/**
 * @author Vibol rim
 */
@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogRepository sysLogRepository;

    @Override
    public SysLog saveLog(HttpServletRequest httpServletRequest, Object requestData, ObjectMapper objectMapper, Object proceed) throws JsonProcessingException {
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
           return sysLogRepository.save(sysLog);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
