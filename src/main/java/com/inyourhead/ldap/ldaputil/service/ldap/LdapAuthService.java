package com.inyourhead.ldap.ldaputil.service.ldap;

import com.inyourhead.ldap.ldaputil.service.AuthService;
import com.inyourhead.ldap.ldaputil.service.AuthType;
import com.inyourhead.ldap.ldaputil.service.exception.AuthenticationException;
import com.inyourhead.ldap.ldaputil.service.exception.ConfigurationException;
import com.inyourhead.ldap.ldaputil.service.model.Config;
import org.springframework.context.annotation.Profile;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.stereotype.Service;

@Profile({"default", "ldap"})
@Service
class LdapAuthService implements AuthService<LdapConfig> {

    @Override
    public boolean authenticate(String username, String password, LdapConfig config) throws AuthenticationException, ConfigurationException {

        return handleError(() -> createAuthenticator(config)
                .authenticate(new UsernamePasswordAuthenticationToken(username, password))
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
        return contextSource;
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
