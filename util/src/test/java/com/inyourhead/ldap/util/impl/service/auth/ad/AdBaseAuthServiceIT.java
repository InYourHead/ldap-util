package com.inyourhead.ldap.util.impl.service.auth.ad;

import com.inyourhead.ldap.util.AdTest;
import com.inyourhead.ldap.util.TestSpringClass;
import com.inyourhead.ldap.util.api.service.auth.ad.AdConfig;
import com.inyourhead.ldap.util.api.service.exception.ConfigurationException;
import com.inyourhead.ldap.util.api.service.model.Credentials;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@EnableConfigurationProperties
@SpringBootTest(classes = TestSpringClass.class)
@AdTest
public class AdBaseAuthServiceIT {

    @Autowired
    private AdAuthService adAuthService;

    @Test
    void beanInitTest() {
        assertNotNull(adAuthService);
    }


    @Test
    void shouldThrowExceptionWhenConnectionIsNotAvailable() {
        Throwable exception = assertThrows(ConfigurationException.class, () -> {
            adAuthService.authenticate(Credentials.of("test", "password"), AdConfig.builder()
                    .urls("http://localhost:111")
                    .baseDn("DC=local")
                    .build());
        });
        assertEquals(ConfigurationException.DEFAULT_MESSAGE, exception.getMessage());
    }
}
