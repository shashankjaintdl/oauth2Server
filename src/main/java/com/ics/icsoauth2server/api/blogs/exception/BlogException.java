package com.ics.icsoauth2server.api.blogs.exception;

import com.ics.icsoauth2server.exception.ICSException;

public class BlogException extends ICSException {

    public BlogException(){}

    public BlogException(String msg){super(msg);}

    public BlogException(String msg,Throwable throwable){super(msg, throwable);}
}
