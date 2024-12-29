package com.inyourhead.ldap.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.ldap.LdapAutoConfiguration;

@SpringBootApplication(exclude = LdapAutoConfiguration.class, scanBasePackages = "com.inyourhead.*")
public class LdapUtilApplication {

    public static void main(String[] args) {
        SpringApplication.run(LdapUtilApplication.class, args);
    }

}
