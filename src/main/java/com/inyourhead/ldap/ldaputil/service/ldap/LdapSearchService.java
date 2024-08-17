package com.inyourhead.ldap.ldaputil.service.ldap;

import com.inyourhead.ldap.ldaputil.service.SearchService;
import com.inyourhead.ldap.ldaputil.service.SearchType;
import com.inyourhead.ldap.ldaputil.service.exception.AuthenticationException;
import com.inyourhead.ldap.ldaputil.service.exception.ConfigurationException;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.stereotype.Service;

@Service
class LdapSearchService implements SearchService<LdapSearchConfig> {

    @Override
    public Object authenticate(String username, String password, LdapSearchConfig config) throws AuthenticationException, ConfigurationException {

        try {
            return createAuthenticator(config).authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof org.springframework.security.core.AuthenticationException) {
                throw new AuthenticationException(e);
            }
            throw new ConfigurationException(e);
        }
    }

    private AuthenticationManager createAuthenticator(LdapSearchConfig config) {
        LdapBindAuthenticationManagerFactory factory = new LdapBindAuthenticationManagerFactory(getContextSource(config));
        factory.setUserDnPatterns(config.getUserDnPatterns().toArray(new String[0]));
        factory.setUserSearchFilter(config.getUserSearchFilter());

        return factory.createAuthenticationManager();
    }

    private BaseLdapPathContextSource getContextSource(LdapSearchConfig config) {
        DefaultSpringSecurityContextSource contextSource = new DefaultSpringSecurityContextSource(config.getUrlsList(), config.getBaseDn());
        contextSource.afterPropertiesSet();
        return contextSource;
    }

    @Override
    public boolean matches(SearchType type) {
        return SearchType.LDAP.equals(type);
    }
}
