package com.ics.icsoauth2server.api.category.exceptions;

import com.ics.icsoauth2server.exception.ICSException;

public class CategoryException extends ICSException {

    public CategoryException(){}

    public CategoryException(String msg){super(msg);}

    public CategoryException(String msg,Throwable throwable){super(msg, throwable);}
}
