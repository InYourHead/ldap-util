package com.inyourhead.ldap.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.inyourhead.ldap.util.*")
public class LdapUtilAutoConfiguration {
}
