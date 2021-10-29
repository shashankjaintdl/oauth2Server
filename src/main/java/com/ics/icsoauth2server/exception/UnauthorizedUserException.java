package com.ics.icsoauth2server.exception;

public class UnauthorizedUserException extends UserException{

    public UnauthorizedUserException(){}

    public UnauthorizedUserException(String msg){super(msg);}

    public UnauthorizedUserException(String msg, Throwable throwable){super(msg,throwable);}
}
