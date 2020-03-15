package com.rvb.springwithapectlog.config;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class AppException {

    @JsonProperty ("status")
    private HttpStatus httpStatus;

    @JsonProperty ("message")
    private String message;

    @JsonProperty ("error_message")
    private List<String> errors;



    public AppException(HttpStatus httpStatus, String message, List<String> error) {
        super();
        this.httpStatus = httpStatus;
        this.message = message;
        this.errors = error;
    }

    public AppException(HttpStatus httpStatus, String message, String error) {
        super();
        this.httpStatus = httpStatus;
        this.message = message;
        this.errors = Arrays.asList(error);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
