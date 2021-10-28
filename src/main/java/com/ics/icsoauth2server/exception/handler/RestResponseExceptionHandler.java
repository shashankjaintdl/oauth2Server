package com.ics.icsoauth2server.exception.handler;

import com.ics.icsoauth2server.exception.UserExistException;
import com.ics.icsoauth2server.http.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.EMPTY_LIST;
import static org.springframework.http.HttpStatus.CONFLICT;

@RestControllerAdvice
public class RestResponseExceptionHandler {

    public static final Logger LOGGER = LoggerFactory.getLogger(RestResponseExceptionHandler.class);
    private APIResponse<Object> apiResponse;
    private List<String> list;

    @ExceptionHandler(value = {UserExistException.class})
    protected ResponseEntity<Object> handleUserAlreadyExistException(RuntimeException ex, HttpServletRequest request){
        list = new ArrayList<>();
        list.add(ex.getMessage());
        apiResponse = new APIResponse<Object>(CONFLICT.value(), CONFLICT.toString(),ex.getMessage(), EMPTY_LIST,request, Arrays.asList(ex.getStackTrace()).stream().limit(10).collect(Collectors.toList()));
        LOGGER.info("{}",ex);
        return ResponseEntity
                .status(apiResponse.getStatusCode())
                .body(apiResponse);
    }



}
