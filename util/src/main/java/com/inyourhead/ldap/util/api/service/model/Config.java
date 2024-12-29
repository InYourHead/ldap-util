package com.inyourhead.ldap.util.api.service.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.*;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class Config implements Serializable {

    /**
     * Comma separated list of urls e.q.: ldap://localhost:389,ldap://example.com
     */
    @NotEmpty
    @Schema(description = "Comma separated list of urls", example = "ldap://localhost:389,ldap://example.com")
    private String urls;

    @JsonAlias({"baseDn", "rootDn"})
    @NotEmpty
    @Schema(description = "Base Dn/ Root Dn", example = "DC=example,DC-local")
    private String baseDn;

    @Schema(description = "Disable SSL certificate validation", example = "false")
    @Builder.Default
    private boolean disableSSLValidation = false;

    @Schema(description = "Override default connection protocol. Leave empty if you want Spring to decide", example = "null")
    @Builder.Default
    @Nullable
    private final SecurityProtocol securityProtocol = null;

    @JsonIgnore
    public Optional<SecurityProtocol> getSecurityProtocolOpt() {
        return Optional.ofNullable(securityProtocol);
    }

    @JsonIgnore
    public List<String> getUrlsList() {
        if (urls == null || urls.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.asList(urls.split(","));
    }

}
