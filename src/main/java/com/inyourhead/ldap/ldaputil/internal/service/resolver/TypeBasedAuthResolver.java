package com.inyourhead.ldap.ldaputil.internal.service.resolver;

import com.inyourhead.ldap.ldaputil.internal.service.auth.AuthService;
import com.inyourhead.ldap.ldaputil.internal.service.exception.AuthenticationException;
import com.inyourhead.ldap.ldaputil.internal.service.exception.ConfigurationException;
import com.inyourhead.ldap.ldaputil.internal.service.model.Config;
import com.inyourhead.ldap.ldaputil.internal.service.model.Credentials;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
class TypeBasedAuthResolver implements AuthResolver {

    private final List<AuthService<?>> authServiceList;

    @Override
    public boolean authorize(Credentials credentials, Config config) throws ConfigurationException, AuthenticationException {
        Optional<AuthService<?>> service = authServiceList.stream().filter(as -> as.matches(config))
                .findFirst();
        if (service.isEmpty()) {
            throw new ConfigurationException("No service found for config " + config);

        }
        return service.get().authenticateUnsafe(credentials, config);
    }
}
