package com.inyourhead.ldap.ldaputil.service.ldap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inyourhead.ldap.ldaputil.service.model.AdminCredentials;
import com.inyourhead.ldap.ldaputil.service.model.Config;
import jakarta.annotation.Nullable;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class LdapConfig extends Config {

    /**
     * List of dn patterns. Empty by default.
     */
    @Builder.Default
    private List<String> userDnPatterns = Collections.emptyList();

    /**
     * User search filter. Common Name by default.
     */
    @Builder.Default
    private String userSearchFilter = "cn={0}";

    /**
     * User search base. Empty by default.
     */
    @Builder.Default
    private String userSearchBase = "";


    @JsonIgnore
    public Optional<AdminCredentials> getAdminCredentialsOpt() {
        return Optional.ofNullable(adminCredentials);
    }

    @Nullable
    private AdminCredentials adminCredentials;
}
