package com.ics.icsoauth2server.exception.handler;

import com.ics.icsoauth2server.http.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.stream.Collectors;

import static java.util.Collections.EMPTY_LIST;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class RestGlobalException extends ResponseEntityExceptionHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(RestGlobalException.class);

    private APIResponse<Object> apiResponse;
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String msg = "";
        if(ex.getFieldError().getDefaultMessage()!=null)
            msg = ex.getFieldError().getDefaultMessage();
        LOGGER.info("{}",ex);
        apiResponse = new APIResponse<Object>(BAD_REQUEST.value(), BAD_REQUEST.toString(),msg,EMPTY_LIST,request,Arrays.asList(ex.getStackTrace()).stream().limit(10).collect(Collectors.toList()));
        return ResponseEntity
                .status(apiResponse.getStatusCode())
                .body(apiResponse);
    }


}
