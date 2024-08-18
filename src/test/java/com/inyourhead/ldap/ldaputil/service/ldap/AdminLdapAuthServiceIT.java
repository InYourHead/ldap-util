package com.inyourhead.ldap.ldaputil.service.ldap;

import com.inyourhead.ldap.ldaputil.service.IntegrationTest;
import com.inyourhead.ldap.ldaputil.service.exception.AuthenticationException;
import com.inyourhead.ldap.ldaputil.service.exception.ConfigurationException;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@EnableConfigurationProperties
@SpringBootTest
@IntegrationTest
public class AdminLdapAuthServiceIT {

    @Autowired
    private LdapAuthService ldapSearchService;

    @Autowired
    @Qualifier("adminSearchConfig")
    private LdapConfig searchConfig;

    @Autowired
    @Qualifier("adminInvalidSearchConfig")
    private LdapConfig invalidSearchConfig;

    @Test
    void beanInitTest() {
        assertNotNull(ldapSearchService);
        assertNotNull(searchConfig);
    }

    @Test
    void shouldAuthenticateValidUser() throws AuthenticationException, ConfigurationException {
        boolean result = ldapSearchService.authenticate("joe", "joePassword1!", searchConfig);

        assertTrue(result);
    }

    @Test
    void shouldThrowExceptionWhenAdminPasswordIsInvalid() {
        Throwable exception = assertThrows(AuthenticationException.class, () -> {
            ldapSearchService.authenticate("joe", "joePassword1!", invalidSearchConfig);
        });
        assertTrue(org.springframework.security.core.AuthenticationException.class.isAssignableFrom(exception.getCause().getClass()));
    }


}
