package com.ics.icsoauth2server.exception;

public class InternalServerException extends ICSException{

    public InternalServerException(){}

    public InternalServerException(String msg){super(msg);}

    public InternalServerException(String msg, Throwable throwable){super(msg,throwable);}
}
