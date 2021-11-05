package com.ics.icsoauth2server.api.subscriber.exception;

public class SubscribeAlreadyExistException extends SubscriberException{

    public SubscribeAlreadyExistException(){}

    public SubscribeAlreadyExistException(String msg){super(msg);}

    public SubscribeAlreadyExistException(String msg, Throwable throwable){super(msg,throwable);}
}
