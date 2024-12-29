package com.inyourhead.ldap.ldaputil.internal.service.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public enum SecurityProtocol {
    SSL("ssl"),
    TLS("tls");

    private final String type;

    SecurityProtocol(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
       return type;
    }
}
