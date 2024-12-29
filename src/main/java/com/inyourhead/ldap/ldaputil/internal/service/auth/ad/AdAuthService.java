package com.inyourhead.ldap.ldaputil.internal.service.auth.ad;

import com.inyourhead.ldap.ldaputil.internal.protocol.TrustAllSocketFactoryProvider;
import com.inyourhead.ldap.ldaputil.internal.service.auth.AuthService;
import com.inyourhead.ldap.ldaputil.internal.service.AuthType;
import com.inyourhead.ldap.ldaputil.internal.service.exception.AuthenticationException;
import com.inyourhead.ldap.ldaputil.internal.service.exception.ConfigurationException;
import com.inyourhead.ldap.ldaputil.internal.service.model.Config;
import com.inyourhead.ldap.ldaputil.internal.service.model.Credentials;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.stereotype.Service;

import javax.naming.Context;
import java.util.HashMap;
import java.util.Map;

@Service
@Profile({"default", "ad"})
class AdAuthService implements AuthService<AdConfig> {

    @Override
    public boolean authenticate(Credentials credentials, AdConfig config) throws AuthenticationException, ConfigurationException {
        return handleError(() -> createAuthenticator(config)
                .authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()))
                .isAuthenticated());
    }


    private AuthenticationProvider createAuthenticator(AdConfig config) {
        ActiveDirectoryLdapAuthenticationProvider factory = new ActiveDirectoryLdapAuthenticationProvider(config.getDomain(), config.getUrls(), config.getBaseDn());
        factory.setSearchFilter(config.getSearchFilter());
        factory.setConvertSubErrorCodesToExceptions(config.isConvertSubErrorCodesToExceptions());
        Map<String, Object> customProperties = getCustomProperties(config);
        if(!customProperties.isEmpty()) {
            factory.setContextEnvironmentProperties(customProperties);
        }
        return factory;
    }

    private Map<String, Object> getCustomProperties(AdConfig config) {
        Map<String, Object> properties = new HashMap<>(config.getContextEnvironmentPropertiesOpt().orElse(new HashMap<>()));
        if (config.isDisableSSLValidation()) {
            properties.put("java.naming.ldap.factory.socket", TrustAllSocketFactoryProvider.provide(config.getSecurityProtocolOpt().orElse(null)));
        }

        config.getSecurityProtocolOpt()
                .ifPresent(sp -> properties.put(Context.SECURITY_PROTOCOL, sp.toString()));
        return properties;
    }


    @Override
    public boolean matches(AuthType type) {
        return AuthType.AD.equals(type);
    }

    @Override
    public boolean matches(Config type) {
        return type instanceof AdConfig;
    }
}
