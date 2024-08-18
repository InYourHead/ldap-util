package com.inyourhead.ldap.ldaputil.service.ldap.config;

import com.inyourhead.ldap.ldaputil.service.ldap.LdapConfig;
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
    public LdapConfig getCnTestLdapSearchConfig() {
        return new LdapConfig();
    }

    @Bean(name = "uidSearchConfig")
    @ConfigurationProperties(prefix = "test.ldap.integration.uid")
    public LdapConfig getUidTestLdapSearchConfig() {
        return new LdapConfig();
    }

    @Bean(name = "adminSearchConfig")
    @ConfigurationProperties(prefix = "test.ldap.integration.admin")
    public LdapConfig getAdminTestLdapSearchConfig() {
        return new LdapConfig();
    }

    @Bean(name = "adminInvalidSearchConfig")
    @ConfigurationProperties(prefix = "test.ldap.integration.admin.invalid")
    public LdapConfig getAdminInvalidTestLdapSearchConfig() {
        return new LdapConfig();
    }
}
