package com.inyourhead.ldap.ldaputil.service.ldap.config;

import com.inyourhead.ldap.ldaputil.service.ldap.LdapSearchConfig;
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

    @Bean(name = "cnSearchConfig")
    @ConfigurationProperties(prefix = "test.ldap.integration.cn")
    public LdapSearchConfig getCnTestLdapSearchConfig() {
        return new LdapSearchConfig();
    }

    @Bean(name = "uidSearchConfig")
    @ConfigurationProperties(prefix = "test.ldap.integration.uid")
    public LdapSearchConfig getUidTestLdapSearchConfig() {
        return new LdapSearchConfig();
    }
}
