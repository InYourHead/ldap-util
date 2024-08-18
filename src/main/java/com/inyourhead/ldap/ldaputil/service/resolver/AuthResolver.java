package com.inyourhead.ldap.ldaputil.service.resolver;

import com.inyourhead.ldap.ldaputil.service.exception.AuthenticationException;
import com.inyourhead.ldap.ldaputil.service.exception.ConfigurationException;
import com.inyourhead.ldap.ldaputil.service.model.Config;

public interface AuthResolver {

    boolean authorize( String username, String password, Config config) throws ConfigurationException, AuthenticationException;
}
