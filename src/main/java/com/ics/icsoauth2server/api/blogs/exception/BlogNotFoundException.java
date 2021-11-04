package com.ics.icsoauth2server.api.blogs.exception;

public class BlogNotFoundException extends BlogException{

    public BlogNotFoundException(){}

    public BlogNotFoundException(String msg){super(msg);}

    public BlogNotFoundException(String msg,Throwable throwable){super(msg, throwable);}
}
