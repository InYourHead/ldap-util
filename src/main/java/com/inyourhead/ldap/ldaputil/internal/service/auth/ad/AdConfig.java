package com.inyourhead.ldap.ldaputil.internal.service.auth.ad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inyourhead.ldap.ldaputil.internal.service.model.Config;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.Optional;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Schema
public class AdConfig extends Config {

    @Nullable
    @Schema(description = "AD domain", example = "example.local")
    private String domain;

    @Builder.Default
    @Schema(description = "Search filter", example = "(&(objectClass=user)(userPrincipalName={0}))")
    private String searchFilter = "(&(objectClass=user)(userPrincipalName={0}))";

    @Builder.Default
    @Schema(description = "Should convert errors to exceptions? By default true", example = "true")
    private boolean convertSubErrorCodesToExceptions = true;

    @Builder.Default
    @Nullable
    @Schema(description = "Custom context environment properties. Null by default", example = "null")
    private Map<String, Object> contextEnvironmentProperties = null;

    @JsonIgnore
    public Optional<Map<String, Object>> getContextEnvironmentPropertiesOpt() {
        if(CollectionUtils.isEmpty(contextEnvironmentProperties)) {
            return Optional.empty();
        }
        return Optional.of(contextEnvironmentProperties);
    }
}
