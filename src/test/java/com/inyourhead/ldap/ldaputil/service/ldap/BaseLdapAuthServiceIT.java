package com.inyourhead.ldap.ldaputil.service.ldap;

import com.inyourhead.ldap.ldaputil.service.IntegrationTest;
import com.inyourhead.ldap.ldaputil.service.exception.ConfigurationException;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@EnableConfigurationProperties
@SpringBootTest
@IntegrationTest
public class BaseLdapAuthServiceIT {

    @Autowired
    private LdapAuthService ldapSearchService;

    @Test
    void beanInitTest() {
        assertNotNull(ldapSearchService);
    }


    @Test
    void shouldThrowExceptionWhenConnectionIsNotAvailable() {
        Throwable exception = assertThrows(ConfigurationException.class, () -> {
            ldapSearchService.authenticate("test", "password", LdapConfig.builder()
                    .urls("http://localhost:111")
                    .baseDn("DC=local")
                    .build());
        });
        assertEquals("Configuration error", exception.getMessage());
    }
}
