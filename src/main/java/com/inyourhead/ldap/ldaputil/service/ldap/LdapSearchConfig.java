package com.inyourhead.ldap.ldaputil.service.ldap;

import com.inyourhead.ldap.ldaputil.service.SearchConfig;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Collections;
import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class LdapSearchConfig extends SearchConfig {

    /**
     * List of dn patterns. Empty by default.
     */
    @Builder.Default
    private List<String> userDnPatterns = Collections.emptyList();

    /**
     * User search filter. Common name by default
     */
    @Builder.Default
    private String userSearchFilter = "cn={0}";
}
