package com.inyourhead.ldap.util.impl.service.auth.ldap;

import com.inyourhead.ldap.util.LdapTest;
import com.inyourhead.ldap.util.TestSpringClass;
import com.inyourhead.ldap.util.api.service.auth.ldap.LdapConfig;
import com.inyourhead.ldap.util.api.service.exception.ConfigurationException;
import com.inyourhead.ldap.util.api.service.model.Credentials;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@EnableConfigurationProperties
@SpringBootTest(classes = TestSpringClass.class)
@LdapTest
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
        assertEquals(ConfigurationException.DEFAULT_MESSAGE, exception.getMessage());
    }
}
