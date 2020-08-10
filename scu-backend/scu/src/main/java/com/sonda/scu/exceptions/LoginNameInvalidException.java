package com.sonda.scu.exceptions;

public class LoginNameInvalidException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public LoginNameInvalidException(String msg) {
        super(msg);
    }

    public LoginNameInvalidException(String msg, Throwable cause) {
        super(msg, cause);
    }

}