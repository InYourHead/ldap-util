package com.inyourhead.ldap.util;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.ldap.LdapAutoConfiguration;

@SpringBootApplication(exclude = LdapAutoConfiguration.class, scanBasePackages = "com.inyourhead.*")
public class TestSpringClass {

    public static void main(String[] args) {
        SpringApplication.run(TestSpringClass.class, args);
    }
}
