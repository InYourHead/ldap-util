package com.inyourhead.ldap.ldaputil.rest;

import com.inyourhead.ldap.ldaputil.service.exception.AuthenticationException;
import com.inyourhead.ldap.ldaputil.service.exception.ConfigurationException;
import com.inyourhead.ldap.ldaputil.service.model.Config;
import com.inyourhead.ldap.ldaputil.service.resolver.AuthResolver;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
public class Controller <T extends Config> {

    private final AuthResolver authResolver;

    public ResponseEntity<Boolean> authorize(
            @RequestParam @NotEmpty String username,
            @RequestParam @NotEmpty String password,
            @RequestBody @NotNull T config) throws ConfigurationException, AuthenticationException {
        return ResponseEntity.ok(authResolver.authorize(username, password, config));
    }
}
