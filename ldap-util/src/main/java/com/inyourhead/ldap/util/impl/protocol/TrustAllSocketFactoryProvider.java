package com.inyourhead.ldap.util.impl.protocol;

import com.inyourhead.ldap.util.api.service.model.SecurityProtocol;
import jakarta.annotation.Nullable;

/**
 * Provides the canonical name of a SocketFactory implementation based on the specified
 * {@link SecurityProtocol}. This class determines the appropriate TrustAllSocketFactory class
 * to use for communication, depending on the provided security protocol (SSL or TLS).
 *
 * The factory class returned by this provider allows creating socket connections while bypassing
 * SSL/TLS certificate validations.
 *
 * If no protocol is provided (null input), this provider defaults to the SSL implementation.
 *
 * The supported protocols and corresponding factory classes are:
 * - {@link SecurityProtocol#TLS}: {@link TrustAllSocketFactoryTLS}
 * - {@link SecurityProtocol#SSL} (default): {@link TrustAllSocketFactorySSL}
 */
public class TrustAllSocketFactoryProvider {

    public static String provide(@Nullable SecurityProtocol protocol) {
        if (protocol == null) {
            return TrustAllSocketFactorySSL.class.getCanonicalName();
        }
        switch (protocol) {
            case TLS: {
                return TrustAllSocketFactoryTLS.class.getCanonicalName();
            }
            case SSL:
            default:
                return TrustAllSocketFactorySSL.class.getCanonicalName();
        }
    }
}
