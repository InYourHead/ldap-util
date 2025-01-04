package com.inyourhead.ldap.util.impl.service.auth.ldap.config;

import com.inyourhead.ldap.util.api.service.auth.ldap.LdapConfig;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Log4j2
@Configuration
@Profile("ldap")
public class LdapConfigProperties {

    @PostConstruct
    public void onInit() {
       log.info("Initialized {}.", this.getClass().getSimpleName());
    }

    @Bean(name = "cnLdapConfig")
    @ConfigurationProperties(prefix = "ldap.cn")
    public LdapConfig getCnTestLdapSearchConfig() {
        return new LdapConfig();
    }

    @Bean(name = "uidLdapConfig")
    @ConfigurationProperties(prefix = "ldap.uid")
    public LdapConfig getUidTestLdapSearchConfig() {
        return new LdapConfig();
    }

    @Bean(name = "adminLdapConfig")
    @ConfigurationProperties(prefix = "ldap.admin")
    public LdapConfig getAdminTestLdapSearchConfig() {
        return new LdapConfig();
    }

    @Bean(name = "adminInvalidLdapConfig")
    @ConfigurationProperties(prefix = "ldap.admin.invalid")
    public LdapConfig getAdminInvalidTestLdapSearchConfig() {
        return new LdapConfig();
    }
}
