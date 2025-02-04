package com.inyourhead.ldap.util.impl.service.auth.ad.config;

import com.inyourhead.ldap.util.api.service.auth.ad.AdConfig;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Log4j2
@Configuration
@Profile("ad")
public class AdConfigProperties {

    @PostConstruct
    public void onInit() {
       log.info("Initialized {}.", this.getClass().getSimpleName());
    }

    @Bean(name = "adConfigWithDomain")
    @ConfigurationProperties(prefix = "ad.domain")
    public AdConfig getAdConfig() {
        return new AdConfig();
    }

    @Bean(name = "adConfigWithoutDomain")
    @ConfigurationProperties(prefix = "ad.nodomain")
    public AdConfig getAdConfigWithoutDomain() {
        return new AdConfig();
    }


}
