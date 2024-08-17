package com.inyourhead.ldap.ldaputil.service.exception;

public class ConfigurationException extends Exception {

    private static final String DEFAULT_MESSAGE = "Configuration error";

    public ConfigurationException(String message) {
        super(message);
    }

    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
    public ConfigurationException(Throwable cause) {
        this(DEFAULT_MESSAGE, cause);
    }

}
