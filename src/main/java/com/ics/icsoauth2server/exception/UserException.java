package com.ics.icsoauth2server.exception;

import net.jcip.annotations.Immutable;

@Immutable
public class UserException extends ICSException{

    public UserException(){}

    public UserException(String msg){super(msg);}

    public UserException(String msg, Throwable throwable){super(msg,throwable);}
}
