package com.inyourhead.ldap.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for automatically setting up components related to LDAP utilities.
 *
 * This class enables component scanning for the specified base package,
 * allowing for the autodetection of beans and components related to LDAP utilities.
 * It is designed to simplify the configuration process by minimizing manual component declarations.
 *
 * To activate this configuration, ensure it is imported or included in the Spring Application context.
 */
@Configuration
@ComponentScan(basePackages = "com.inyourhead.ldap.util.*")
public class LdapUtilAutoConfiguration {
}
