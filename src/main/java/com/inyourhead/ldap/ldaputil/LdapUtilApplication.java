package com.inyourhead.ldap.ldaputil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.ldap.LdapAutoConfiguration;

@SpringBootApplication(exclude = LdapAutoConfiguration.class)
public class LdapUtilApplication {

    public static void main(String[] args) {
        SpringApplication.run(LdapUtilApplication.class, args);
    }

}
