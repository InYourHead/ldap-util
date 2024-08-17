package com.inyourhead.ldap.ldaputil.service;

import com.inyourhead.ldap.ldaputil.service.exception.AuthenticationException;
import com.inyourhead.ldap.ldaputil.service.exception.ConfigurationException;

public interface SearchService<T extends SearchConfig> {

    boolean authenticate(String username, String password, T config) throws AuthenticationException, ConfigurationException;

    boolean matches(SearchType type);

}
