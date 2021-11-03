package com.ics.icsoauth2server.exception.handler;

import com.ics.icsoauth2server.api.tag.exceptions.TagNotFoundExceptions;
import com.ics.icsoauth2server.exception.InternalServerException;
import com.ics.icsoauth2server.exception.UnauthorizedUserException;
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
import static org.springframework.http.HttpStatus.*;

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


    @ExceptionHandler(value = {UnauthorizedUserException.class})
    protected ResponseEntity<Object> handleUnauthorizedUserException(RuntimeException ex, HttpServletRequest request){
        list = new ArrayList<>();
        list.add(ex.getMessage());
        apiResponse = new APIResponse<Object>(FORBIDDEN.value(), FORBIDDEN.toString(),ex.getMessage(), EMPTY_LIST,request, Arrays.asList(ex.getStackTrace()).stream().limit(10).collect(Collectors.toList()));
        LOGGER.info("{}",ex);
        return ResponseEntity
                .status(apiResponse.getStatusCode())
                .body(apiResponse);
    }

    @ExceptionHandler(value = {TagNotFoundExceptions.class})
    protected ResponseEntity<Object> handleResourceNotFoundException(RuntimeException ex,HttpServletRequest request){
        list = new ArrayList<>();
        list.add(ex.getMessage());
        apiResponse = new APIResponse<Object>(NOT_FOUND.value(), NOT_FOUND.toString(),ex.getMessage(), EMPTY_LIST,request, Arrays.asList(ex.getStackTrace()).stream().limit(10).collect(Collectors.toList()));
        LOGGER.info("{}",ex);
        return ResponseEntity
                .status(apiResponse.getStatusCode())
                .body(apiResponse);
    }

    @ExceptionHandler(value = {InternalServerException.class})
    protected ResponseEntity<Object> handleInternalException(RuntimeException ex, HttpServletRequest request){
        list = new ArrayList<>();
        list.add(ex.getMessage());
        apiResponse = new APIResponse<Object>(INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR.toString(),ex.getMessage(), EMPTY_LIST,request, Arrays.asList(ex.getStackTrace()).stream().limit(10).collect(Collectors.toList()));
        LOGGER.info("{}",ex);
        return ResponseEntity
                .status(apiResponse.getStatusCode())
                .body(apiResponse);
    }


}
