package com.inyourhead.ldap.util.impl.service.auth.ldap;

import com.inyourhead.ldap.util.LdapTest;
import com.inyourhead.ldap.util.TestSpringClass;
import com.inyourhead.ldap.util.api.service.auth.ldap.LdapConfig;
import com.inyourhead.ldap.util.api.service.exception.AuthenticationException;
import com.inyourhead.ldap.util.api.service.exception.ConfigurationException;
import com.inyourhead.ldap.util.api.service.model.Credentials;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@EnableConfigurationProperties
@SpringBootTest(classes = TestSpringClass.class)
@LdapTest
public class LdapAdminCredentialsAuthServiceIT {

    @Autowired
    private LdapAuthService ldapSearchService;

    @Autowired
    @Qualifier("adminLdapConfig")
    private LdapConfig searchConfig;

    @Autowired
    @Qualifier("adminInvalidLdapConfig")
    private LdapConfig invalidSearchConfig;

    @Test
    void beanInitTest() {
        assertNotNull(ldapSearchService);
        assertNotNull(searchConfig);
    }

    @Test
    void shouldAuthenticateValidUser() throws AuthenticationException, ConfigurationException {
        boolean result = ldapSearchService.authenticate(Credentials.of("joe", "joePassword1!"), searchConfig);

        assertTrue(result);
    }

    @Test
    void shouldThrowExceptionWhenAdminPasswordIsInvalid() {
        Throwable exception = assertThrows(AuthenticationException.class, () -> {
            ldapSearchService.authenticate(Credentials.of("joe", "joePassword1!"), invalidSearchConfig);
        });
        assertTrue(org.springframework.security.core.AuthenticationException.class.isAssignableFrom(exception.getCause().getClass()));
    }


}
