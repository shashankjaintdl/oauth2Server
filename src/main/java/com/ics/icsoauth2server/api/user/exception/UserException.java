package com.ics.icsoauth2server.api.user.exception;

import net.jcip.annotations.Immutable;

@Immutable
public class UserException extends RuntimeException{

    public UserException(){}

    public UserException(String msg){super(msg);}

    public UserException(String msg, Throwable throwable){super(msg,throwable);}
}
