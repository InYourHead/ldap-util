package com.inyourhead.ldap.ldaputil.internal.service.exception;

public class AuthenticationException extends Exception {

    private static final String DEFAULT_MESSAGE = "Authentication error";

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthenticationException(Throwable cause) {
        this(DEFAULT_MESSAGE, cause);
    }
}
