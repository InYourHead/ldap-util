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
import org.springframework.security.authentication.BadCredentialsException;
import static org.junit.jupiter.api.Assertions.*;

@EnableConfigurationProperties
@SpringBootTest(classes = TestSpringClass.class)
@LdapTest
public class LdapCnAuthServiceIT {

    @Autowired
    private LdapAuthService ldapSearchService;

    @Autowired
    @Qualifier("cnLdapConfig")
    private LdapConfig searchConfig;

    @Test
    void beanInitTest() {
        assertNotNull(ldapSearchService);
        assertNotNull(searchConfig);
    }

    @Test
    void shouldThrowExceptionWhenUserPasswordIsInvalid() {
        Throwable exception = assertThrows(AuthenticationException.class, () -> {
            ldapSearchService.authenticate(Credentials.of("joe", "password"), searchConfig);
        });
        assertInstanceOf(BadCredentialsException.class, exception.getCause());
    }

    @Test
    void shouldThrowExceptionWhenUserDoesNotExists() {
        Throwable exception = assertThrows(AuthenticationException.class, () -> {
            ldapSearchService.authenticate(Credentials.of("test", "password"), searchConfig);
        });
        assertInstanceOf(BadCredentialsException.class, exception.getCause());
    }

    @Test
    void shouldAuthenticateValidUser() throws AuthenticationException, ConfigurationException {
        boolean result = ldapSearchService.authenticate(Credentials.of("joe", "joePassword1!"), searchConfig);

        assertTrue(result);
    }


}
