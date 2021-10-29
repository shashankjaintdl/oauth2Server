package com.ics.icsoauth2server.exception;

public class ICSException extends RuntimeException{

    public ICSException(){}

    public ICSException(String msg){super(msg);}

    public ICSException(String msg,Throwable throwable){super(msg,throwable);}
}
