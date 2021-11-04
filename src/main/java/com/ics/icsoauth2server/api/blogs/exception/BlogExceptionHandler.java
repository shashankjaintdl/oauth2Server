package com.ics.icsoauth2server.api.blogs.exception;

import com.ics.icsoauth2server.http.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.EMPTY_LIST;
import static org.springframework.http.HttpStatus.CONFLICT;

@RestControllerAdvice(basePackages = "com.ics.icsoauth2server.blog")
public class BlogExceptionHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(BlogExceptionHandler.class);
    private APIResponse<Object> apiResponse;
    private List<String> list;

    @ExceptionHandler({BlogAlreadyExistException.class})
    protected ResponseEntity<Object> handleBlogAlreadyExistException(BlogException ex, HttpServletRequest request){
        apiResponse = new APIResponse<Object>(CONFLICT.value(), CONFLICT.toString(),ex.getMessage(), EMPTY_LIST,request, Arrays.asList(ex.getStackTrace()).stream().limit(10).collect(Collectors.toList()));
        LOGGER.info("{}",ex);
        return ResponseEntity
                .status(apiResponse.getStatusCode())
                .body(apiResponse);
    }
}
