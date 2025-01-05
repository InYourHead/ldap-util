package com.inyourhead.ldap.util.api.service.auth;

import com.inyourhead.ldap.util.api.service.exception.AuthenticationException;
import com.inyourhead.ldap.util.api.service.exception.ConfigurationException;
import com.inyourhead.ldap.util.api.service.model.Config;
import com.inyourhead.ldap.util.api.service.model.Credentials;

import java.util.function.Supplier;

public interface AuthService<T extends Config> {

    /**
     * Authenticates a user based on the provided credentials and configuration.
     *
     * @param credentials The credentials containing the username and optionally the password for authentication.
     * @param config The configuration details required for authentication.
     * @return true if the authentication is successful, false otherwise.
     * @throws AuthenticationException If an error occurs during the authentication process.
     * @throws ConfigurationException If there is an issue with the provided configuration.
     */
    boolean authenticate(Credentials credentials, T config) throws AuthenticationException, ConfigurationException;


    default boolean handleError(Supplier<Boolean> authentication) throws AuthenticationException, ConfigurationException {
        try {
            return authentication.get();
        } catch (Exception e) {
            if (e instanceof org.springframework.security.core.AuthenticationException) {
                throw new AuthenticationException(e);
            }
            throw new ConfigurationException(e);
        }
    }
}
