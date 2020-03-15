package com.rvb.springwithapectlog.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()){
            errors.add(fieldError.getField() + ":" + fieldError.getDefaultMessage());
        }
        for (ObjectError objectError : ex.getBindingResult().getGlobalErrors()){
            errors.add(objectError.getObjectName() + ":" + objectError.getDefaultMessage());
        }
        AppException appException = new AppException(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(),errors);
        return handleExceptionInternal(
                ex,
                appException,
                headers,
                appException.getHttpStatus(),
                request
        );
    }

    @Override
    protected  ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException parameterException,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest webRequest){
        AppException appException = new AppException(
                HttpStatus.BAD_REQUEST,
                parameterException.getLocalizedMessage(),
                "Parameter Name: "+ parameterException.getParameterName() + "Type:" + parameterException.getParameterType() + "is missing ."

        );
        return  new ResponseEntity<>(appException,new HttpHeaders(),appException.getHttpStatus());
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        String error =
               "Parameter Name: "+ ex.getName() + " Should be type of:" + ex.getRequiredType().getName();

        AppException appException =
                new AppException(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(
                appException, new HttpHeaders(), appException.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t + ", "));

        AppException apiError = new AppException(HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                ex.getLocalizedMessage(), builder.substring(0, builder.length() - 2));
        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getHttpStatus());
    }
}
