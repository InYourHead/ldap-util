package com.inyourhead.ldap.ldaputil.internal.service.auth.ldap;

import com.inyourhead.ldap.ldaputil.IntegrationTest;
import com.inyourhead.ldap.ldaputil.internal.service.exception.ConfigurationException;
import com.inyourhead.ldap.ldaputil.internal.service.model.Credentials;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@EnableConfigurationProperties
@SpringBootTest
@IntegrationTest
public class LdapBaseAuthServiceIT {

    @Autowired
    private LdapAuthService ldapSearchService;

    @Test
    void beanInitTest() {
        assertNotNull(ldapSearchService);
    }


    @Test
    void shouldThrowExceptionWhenConnectionIsNotAvailable() {
        Throwable exception = assertThrows(ConfigurationException.class, () -> {
            ldapSearchService.authenticate(Credentials.of("test", "password"), LdapConfig.builder()
                    .urls("http://localhost:111")
                    .baseDn("DC=local")
                    .build());
        });
        assertEquals("Configuration error", exception.getMessage());
    }
}
