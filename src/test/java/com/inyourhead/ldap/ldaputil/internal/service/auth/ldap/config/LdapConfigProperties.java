package com.inyourhead.ldap.ldaputil.internal.service.auth.ldap.config;

import com.inyourhead.ldap.ldaputil.internal.service.auth.ldap.LdapConfig;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Log4j2
@Configuration
@PropertySource("classpath:ldap/ldap-config.yml")
public class LdapConfigProperties {

    @PostConstruct
    public void onInit() {
       log.info("Initialized {}.", this.getClass().getSimpleName());
    }

    @Bean(name = "cnLdapConfig")
    @ConfigurationProperties(prefix = "authentication.cn")
    public LdapConfig getCnTestLdapSearchConfig() {
        return new LdapConfig();
    }

    @Bean(name = "uidLdapConfig")
    @ConfigurationProperties(prefix = "authentication.uid")
    public LdapConfig getUidTestLdapSearchConfig() {
        return new LdapConfig();
    }

    @Bean(name = "adminLdapConfig")
    @ConfigurationProperties(prefix = "authentication.admin")
    public LdapConfig getAdminTestLdapSearchConfig() {
        return new LdapConfig();
    }

    @Bean(name = "adminInvalidLdapConfig")
    @ConfigurationProperties(prefix = "authentication.admin.invalid")
    public LdapConfig getAdminInvalidTestLdapSearchConfig() {
        return new LdapConfig();
    }
}
