package com.ics.icsoauth2server.api.subscriber.exception;

import com.ics.icsoauth2server.api.blogs.exception.BlogExceptionHandler;
import com.ics.icsoauth2server.http.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class SubscriberExceptionHandler {


    private final static Logger LOGGER = LoggerFactory.getLogger(BlogExceptionHandler.class);
    private APIResponse<Object> apiResponse;
    private List<String> list;

    @ExceptionHandler({SubscribeAlreadyExistException.class})
    protected ResponseEntity<Object> handleAlreadySubscribeException(SubscriberException ex, HttpServletRequest request){
        LOGGER.info("{}",ex.getMessage());
        apiResponse = new APIResponse(
                OK.value(),
                OK.toString(),
                ex.getMessage(),
                EMPTY_LIST,
                request
        );
        return ResponseEntity
                .status(apiResponse.getStatusCode()).body(apiResponse);
    }


}
