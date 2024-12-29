package com.inyourhead.ldap.ldaputil.internal.service.auth.ldap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inyourhead.ldap.ldaputil.internal.service.model.Credentials;
import com.inyourhead.ldap.ldaputil.internal.service.model.Config;
import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema
public class LdapConfig extends Config {

    /**
     * List of dn patterns. Empty by default.
     */
    @Builder.Default
    @ArraySchema(contains = @Schema( description = "List of user dn patterns. Empty by default.",
            examples = {"uid={0},ou=people"}), uniqueItems = true, minItems = 0)
    private List<String> userDnPatterns = Collections.emptyList();

    /**
     * User search filter. Common Name by default.
     */
    @Builder.Default
    @Schema(description = "User search filter. Common Name by default.", example = "cn={0}")
    private String userSearchFilter = "cn={0}";

    /**
     * User search base. Empty by default.
     */
    @Builder.Default
    @Schema(description = "User search base. Empty by default.", example = "null", minimum = "0")
    private String userSearchBase = "";


    @JsonIgnore
    public Optional<Credentials> getAdminCredentialsOpt() {
        return Optional.ofNullable(adminCredentials);
    }

    @Nullable
    @Schema(description = "Admin credentials. By default empty.", example = "null")
    private Credentials adminCredentials;

    @Nullable
    @Schema(description = "Authentication strategy class. By default empty. See DirContextAuthenticationStrategy", example = "null")
    private String authenticationStrategy;

    public Optional<String> getAuthenticationStrategyOpt() {
        return StringUtils.isNotBlank(authenticationStrategy) ? Optional.of(authenticationStrategy) : Optional.empty();
    }
}
