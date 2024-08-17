package com.inyourhead.ldap.ldaputil.service.ldap;

import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
public class TestConfigProperties {

    @PostConstruct
    public void onInit() {
       log.info("Initialized {}.", this.getClass().getSimpleName());
    }

    @Bean(name = "firstSearchConfig")
    @ConfigurationProperties(prefix = "test.ldap.integration.first")
    public LdapSearchConfig getFirstTestLdapSearchConfig() {
        return new LdapSearchConfig();
    }
}
