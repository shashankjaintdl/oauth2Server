package com.ics.icsoauth2server.api.tag.exceptions;

import com.ics.icsoauth2server.exception.ICSException;

public class TagException extends ICSException {

    public TagException(){}

    public TagException(String msg){super(msg);}

    public TagException(String msg, Throwable throwable){super(msg, throwable);}
}
