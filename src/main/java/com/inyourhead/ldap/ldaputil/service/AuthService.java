package com.inyourhead.ldap.ldaputil.service;

import com.inyourhead.ldap.ldaputil.service.exception.AuthenticationException;
import com.inyourhead.ldap.ldaputil.service.exception.ConfigurationException;
import com.inyourhead.ldap.ldaputil.service.model.Config;

import java.util.function.Supplier;

public interface AuthService<T extends Config> {

    boolean authenticate(String username, String password, T config) throws AuthenticationException, ConfigurationException;
    default boolean authenticateUnsafe(String username, String password, Config config) throws AuthenticationException, ConfigurationException {
        return authenticate(username, password, (T) config);
    }

    boolean matches(AuthType type);
    boolean matches(Config type);

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
