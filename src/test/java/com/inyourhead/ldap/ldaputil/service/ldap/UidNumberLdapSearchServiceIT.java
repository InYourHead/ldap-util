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
public class UidNumberLdapSearchServiceIT {

    @Autowired
    private LdapSearchService ldapSearchService;

    @Autowired
    @Qualifier("uidSearchConfig")
    private LdapSearchConfig searchConfig;

    @Test
    void beanInitTest() {
        assertNotNull(ldapSearchService);
        assertNotNull(searchConfig);
    }

    @Test
    void shouldAuthenticateValidUser() throws AuthenticationException, ConfigurationException {
        boolean result = ldapSearchService.authenticate("1000", "joePassword1!", searchConfig);

        assertTrue(result);
    }


}
