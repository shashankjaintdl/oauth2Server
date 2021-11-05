package com.ics.icsoauth2server.api.subscriber.exception;

import com.ics.icsoauth2server.exception.ICSException;

public class SubscriberException extends ICSException {

    public SubscriberException(){}

    public SubscriberException(String msg){super(msg);}

    public SubscriberException(String msg,Throwable throwable){super(msg,throwable);}
}
