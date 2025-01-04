package com.inyourhead.ldap.util.impl.protocol;

import com.inyourhead.ldap.util.api.service.model.SecurityProtocol;
import jakarta.annotation.Nullable;

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
