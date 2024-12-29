package com.inyourhead.ldap.ldaputil.internal.service.auth;

import com.inyourhead.ldap.ldaputil.internal.service.AuthType;
import com.inyourhead.ldap.ldaputil.internal.service.exception.AuthenticationException;
import com.inyourhead.ldap.ldaputil.internal.service.exception.ConfigurationException;
import com.inyourhead.ldap.ldaputil.internal.service.model.Config;
import com.inyourhead.ldap.ldaputil.internal.service.model.Credentials;

import java.util.function.Supplier;

public interface AuthService<T extends Config> {

    boolean authenticate(Credentials credentials, T config) throws AuthenticationException, ConfigurationException;

    default boolean authenticateUnsafe(Credentials credentials, Config config) throws AuthenticationException, ConfigurationException {
        return authenticate(credentials, (T) config);
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
