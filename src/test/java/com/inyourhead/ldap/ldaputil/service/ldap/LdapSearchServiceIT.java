package com.inyourhead.ldap.ldaputil.service.ldap;

import com.inyourhead.ldap.ldaputil.service.IntegrationTest;
import com.inyourhead.ldap.ldaputil.service.exception.AuthenticationException;
import com.inyourhead.ldap.ldaputil.service.exception.ConfigurationException;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@EnableConfigurationProperties
@SpringBootTest
@IntegrationTest
@Tag("integration")
public class LdapSearchServiceIT {

    @Autowired
    private LdapSearchService ldapSearchService;

    @Autowired
    @Qualifier("firstSearchConfig")
    private LdapSearchConfig firsSearchConfig;

    @Test
    void beanInitTest() {
        assertNotNull(ldapSearchService);
        assertNotNull(firsSearchConfig);
    }

    @Test
    void shouldThrowExceptionWhenConnectionIsNotAvailable() {
        Throwable exception = assertThrows(ConfigurationException.class, () -> {
            ldapSearchService.authenticate("test", "password", LdapSearchConfig.builder()
                    .urls("http://localhost:111")
                    .baseDn("DC=local")
                    .build());
        });
        assertEquals("Configuration error", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenUserPasswordIsInvalid() {
        Throwable exception = assertThrows(AuthenticationException.class, () -> {
            ldapSearchService.authenticate("joe", "password", firsSearchConfig);
        });
        assertInstanceOf(BadCredentialsException.class, exception.getCause());
    }

    @Test
    void shouldThrowExceptionWhenUserDoesNotExists() {
        Throwable exception = assertThrows(AuthenticationException.class, () -> {
            ldapSearchService.authenticate("test", "password", firsSearchConfig);
        });
        assertInstanceOf(BadCredentialsException.class, exception.getCause());
    }

    @Test
    void shouldAuthenticateValidUser() throws AuthenticationException, ConfigurationException {
        Object result = ldapSearchService.authenticate("joe", "joePassword1!", firsSearchConfig);

        assertNotNull(result);
    }


}
