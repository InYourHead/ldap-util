package com.inyourhead.ldap.app.rest;

import com.inyourhead.ldap.util.api.service.auth.AuthService;
import com.inyourhead.ldap.util.api.service.exception.AuthenticationException;
import com.inyourhead.ldap.util.api.service.exception.ConfigurationException;
import com.inyourhead.ldap.util.api.service.model.Config;
import com.inyourhead.ldap.util.api.service.model.Credentials;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
public class Controller<T extends Config> {

    private final AuthService<T> authService;

    public ResponseEntity<Boolean> authenticate(
            @Valid @NotNull Credentials credentials,
            @Valid @NotNull T config) throws ConfigurationException, AuthenticationException {
        return ResponseEntity.ok(authService.authenticate(
                credentials,
                config));
    }
}
