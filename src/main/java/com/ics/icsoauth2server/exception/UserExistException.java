package com.ics.icsoauth2server.exception;

public class UserExistException extends UserException{

    public UserExistException() {
        super();
    }

    public UserExistException(String msg) {
        super(msg);
    }

    public UserExistException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
