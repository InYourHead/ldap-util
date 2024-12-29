package com.inyourhead.ldap.util.impl.service.auth.ad;

import com.inyourhead.ldap.util.AdTest;
import com.inyourhead.ldap.util.TestSpringClass;
import com.inyourhead.ldap.util.api.service.auth.ad.AdConfig;
import com.inyourhead.ldap.util.api.service.exception.AuthenticationException;
import com.inyourhead.ldap.util.api.service.exception.ConfigurationException;
import com.inyourhead.ldap.util.api.service.model.Credentials;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@EnableConfigurationProperties
@SpringBootTest(classes = TestSpringClass.class)
@AdTest
public class AdWithDomainAuthServiceIT {

    @Autowired
    private AdAuthService adAuthService;

    @Autowired
    @Qualifier("adConfigWithDomain")
    private AdConfig adConfig;


    @Test
    void beanInitTest() {
        assertNotNull(adAuthService);
        assertNotNull(adConfig);
    }

    @Test
    void shouldAuthenticateValidUserWithDomainParam() throws AuthenticationException, ConfigurationException {
        boolean result = adAuthService.authenticate(Credentials.of("jsmith", "joePassword1!"), adConfig);

        assertTrue(result);
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsInvalid() {
        Throwable exception = assertThrows(AuthenticationException.class, () -> {
            adAuthService.authenticate(Credentials.of("jsmith", "invalidPassword!"), adConfig);
        });
        assertTrue(org.springframework.security.core.AuthenticationException.class.isAssignableFrom(exception.getCause().getClass()));
    }
}
