package com.inyourhead.ldap.ldaputil.rest;

import com.inyourhead.ldap.ldaputil.service.exception.AuthenticationException;
import com.inyourhead.ldap.ldaputil.service.exception.ConfigurationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ConfigurationException.class)
    public ResponseEntity<ExceptionMessage> handleConfigurationException(ConfigurationException configurationException) {
        return new ResponseEntity<>(ExceptionMessage.builder()
                .message(configurationException.getMessage())
                .cause(configurationException.getCause() != null ? configurationException.getCause().getMessage() : null)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionMessage> handleAuthenticationException(AuthenticationException configurationException) {
        return new ResponseEntity<>(ExceptionMessage.builder()
                .message(configurationException.getMessage())
                .cause(configurationException.getCause() != null ? configurationException.getCause().getMessage() : null)
                .build(), HttpStatus.UNAUTHORIZED);
    }
}
