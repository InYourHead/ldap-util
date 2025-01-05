package com.inyourhead.ldap.util.api.service.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Enum representing security protocols used in secure communication.
 * It provides supported types of security protocols to be used, such as SSL and TLS.
 * This enumeration maps protocol types to their corresponding string representation.
 *
 * Each constant in this enumeration has an associated string, which is returned when the
 * {@link #toString()} method is called.
 *
 * Usage of this enumeration includes specifying the security protocol for various configurations
 * or functionalities that require protocol-specific handling.
 */
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
