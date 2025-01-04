package com.inyourhead.ldap.app;

import com.inyourhead.ldap.config.LdapUtilAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(LdapUtilAutoConfiguration.class)
public class LdapUtilApplication {

    public static void main(String[] args) {
        SpringApplication.run(LdapUtilApplication.class, args);
    }

}
