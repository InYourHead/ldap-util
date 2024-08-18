package com.inyourhead.ldap.ldaputil.service.resolver;

import com.inyourhead.ldap.ldaputil.service.AuthService;
import com.inyourhead.ldap.ldaputil.service.exception.AuthenticationException;
import com.inyourhead.ldap.ldaputil.service.exception.ConfigurationException;
import com.inyourhead.ldap.ldaputil.service.model.Config;
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
    public boolean authorize(String username, String password, Config config) throws ConfigurationException, AuthenticationException {
        Optional<AuthService<?>> service = authServiceList.stream().filter(as -> as.matches(config))
                .findFirst();
        if (service.isEmpty()) {
            throw new ConfigurationException("No service found for config " + config);

        }
        return service.get().authenticateUnsafe(username, password, config);
    }
}
