package com.inyourhead.ldap.ldaputil.internal.service.resolver;

import com.inyourhead.ldap.ldaputil.internal.service.exception.AuthenticationException;
import com.inyourhead.ldap.ldaputil.internal.service.exception.ConfigurationException;
import com.inyourhead.ldap.ldaputil.internal.service.model.Config;
import com.inyourhead.ldap.ldaputil.internal.service.model.Credentials;

public interface AuthResolver {

    boolean authorize(Credentials credentials, Config config) throws ConfigurationException, AuthenticationException;
}
