package com.ics.icsoauth2server.api.tag.exceptions;

public class TagNotFoundExceptions extends TagException{
    public TagNotFoundExceptions(){}

    public TagNotFoundExceptions(String msg){super(msg);}

    public TagNotFoundExceptions(String msg,Throwable throwable){super(msg,throwable);}
}
