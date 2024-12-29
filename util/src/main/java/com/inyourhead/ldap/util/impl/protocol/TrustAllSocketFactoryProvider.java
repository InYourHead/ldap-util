package com.inyourhead.ldap.util.impl.protocol;

import com.inyourhead.ldap.util.api.service.model.SecurityProtocol;
import jakarta.annotation.Nullable;

public class TrustAllSocketFactoryProvider {

    public static String provide(@Nullable SecurityProtocol protocol) {
        return switch (protocol) {
            case SecurityProtocol.TLS -> TrustAllSocketFactoryTLS.class.getCanonicalName();
            case null, default -> TrustAllSocketFactorySSL.class.getCanonicalName();
        };
    }
}
