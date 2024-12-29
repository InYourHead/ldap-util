package com.inyourhead.ldap.ldaputil.internal.service.auth.ldap;

import com.inyourhead.ldap.ldaputil.internal.protocol.TrustAllSocketFactoryProvider;
import com.inyourhead.ldap.ldaputil.internal.service.auth.AuthService;
import com.inyourhead.ldap.ldaputil.internal.service.AuthType;
import com.inyourhead.ldap.ldaputil.internal.service.exception.AuthenticationException;
import com.inyourhead.ldap.ldaputil.internal.service.exception.ConfigurationException;
import com.inyourhead.ldap.ldaputil.internal.service.model.Config;
import com.inyourhead.ldap.ldaputil.internal.service.model.Credentials;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.ldap.core.support.DirContextAuthenticationStrategy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.stereotype.Service;

import javax.naming.Context;
import java.util.HashMap;
import java.util.Map;

@Profile({"default", "ldap"})
@Service
@Log4j2
class LdapAuthService implements AuthService<LdapConfig> {

    @Override
    public boolean authenticate(Credentials credentials, LdapConfig config) throws AuthenticationException, ConfigurationException {

        return handleError(() -> createAuthenticator(config)
                .authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()))
                .isAuthenticated());
    }

    private AuthenticationManager createAuthenticator(LdapConfig config) {
        LdapBindAuthenticationManagerFactory factory = new LdapBindAuthenticationManagerFactory(getContextSource(config));
        factory.setUserDnPatterns(config.getUserDnPatterns().toArray(new String[0]));
        factory.setUserSearchFilter(config.getUserSearchFilter());
        factory.setUserSearchBase(config.getUserSearchBase());

        return factory.createAuthenticationManager();
    }

    private BaseLdapPathContextSource getContextSource(LdapConfig config) {
        DefaultSpringSecurityContextSource contextSource = new DefaultSpringSecurityContextSource(config.getUrlsList(), config.getBaseDn());
        config.getAdminCredentialsOpt().ifPresent(ac -> {
            contextSource.setUserDn(ac.getUsername());
            contextSource.setPassword(ac.getPassword());
        });
        contextSource.afterPropertiesSet();

        Map<String, Object> customProperties = getCustomProperties(config);
        if(!customProperties.isEmpty()) {
            contextSource.setBaseEnvironmentProperties(customProperties);
        }

        applyCustomAuthenticationStrategy(config, contextSource);

        return contextSource;
    }

    private Map<String, Object> getCustomProperties(LdapConfig config) {
        Map<String, Object> properties = new HashMap<>();

        if (config.isDisableSSLValidation()) {
            properties.put("java.naming.ldap.factory.socket", TrustAllSocketFactoryProvider.provide(config.getSecurityProtocolOpt().orElse(null)));
        }

        config.getSecurityProtocolOpt().ifPresent(sp -> properties.put(Context.SECURITY_PROTOCOL, sp.toString()));

        return properties;
    }

    private void applyCustomAuthenticationStrategy(LdapConfig config, DefaultSpringSecurityContextSource contextSource) {
        config.getAuthenticationStrategyOpt().ifPresent(as -> {
            log.info("Trying to set authentication strategy to {}", as);
            try {
                contextSource.setAuthenticationStrategy((DirContextAuthenticationStrategy) Class.forName(as).getConstructor().newInstance());
            } catch (Exception e) {
                log.error("Unable to create new instance of {}. Does this class have default constructor?", as, e);
            }
        });
    }

    @Override
    public boolean matches(AuthType type) {
        return AuthType.LDAP.equals(type);
    }

    @Override
    public boolean matches(Config type) {
        return type instanceof LdapConfig;
    }
}
