package com.inyourhead.ldap.ldaputil.service.ad;

import com.inyourhead.ldap.ldaputil.service.AuthService;
import com.inyourhead.ldap.ldaputil.service.AuthType;
import com.inyourhead.ldap.ldaputil.service.exception.AuthenticationException;
import com.inyourhead.ldap.ldaputil.service.exception.ConfigurationException;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "ad"})
class AdAuthService implements AuthService<AdConfig> {

    @Override
    public boolean authenticate(String username, String password, AdConfig config) throws AuthenticationException, ConfigurationException {
        return handleError(() -> createAuthenticator(config)
                .authenticate(new UsernamePasswordAuthenticationToken(username, password))
                .isAuthenticated());
    }


    private AuthenticationProvider createAuthenticator(AdConfig config) {
        ActiveDirectoryLdapAuthenticationProvider factory = new ActiveDirectoryLdapAuthenticationProvider(config.getDomain(), config.getUrls(), config.getBaseDn());
        factory.setSearchFilter(config.getSearchFilter());
        factory.setUseAuthenticationRequestCredentials(config.isUseAuthenticationRequestCredentials());
        factory.setConvertSubErrorCodesToExceptions(config.isConvertSubErrorCodesToExceptions());
        config.getContextEnvironmentPropertiesOpt().ifPresent(factory::setContextEnvironmentProperties);
        return factory;
    }


    @Override
    public boolean matches(AuthType type) {
        return AuthType.AD.equals(type);
    }
}
