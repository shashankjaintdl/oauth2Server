package com.ics.icsoauth2server.api.blogs.exception;

public class BlogAlreadyExistException extends BlogException{

    public BlogAlreadyExistException(){}

    public BlogAlreadyExistException(String msg){super(msg);}

    public BlogAlreadyExistException(String msg,Throwable throwable){super(msg, throwable);}
}
