package com.inyourhead.ldap.ldaputil.service;

import org.junit.jupiter.api.Tag;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Tag("unit")
public @interface UnitTest {
}
