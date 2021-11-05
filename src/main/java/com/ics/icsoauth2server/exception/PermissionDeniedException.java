package com.ics.icsoauth2server.exception;

import com.ics.icsoauth2server.exception.ICSException;

public class PermissionDeniedException extends ICSException {

    public PermissionDeniedException(){}

    public PermissionDeniedException(String msg){super(msg);}

    public PermissionDeniedException(String msg,Throwable throwable){super(msg,throwable);}
}
