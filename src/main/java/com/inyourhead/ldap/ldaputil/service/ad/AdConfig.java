package com.inyourhead.ldap.ldaputil.service.ad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inyourhead.ldap.ldaputil.service.model.Config;
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
public class AdConfig extends Config {

    @Nullable
    private String domain;

    @Override
    public String getBaseDn() {
        return super.getBaseDn();
    }

    @Builder.Default
    private String searchFilter = "(&(objectClass=user)(userPrincipalName={0}))";

    @Builder.Default
    private boolean useAuthenticationRequestCredentials = true;

    @Builder.Default
    private boolean convertSubErrorCodesToExceptions = true;

    @Builder.Default
    @Nullable
    private Map<String, Object> contextEnvironmentProperties = null;

    @JsonIgnore
    public Optional<Map<String, Object>> getContextEnvironmentPropertiesOpt() {
        if(CollectionUtils.isEmpty(contextEnvironmentProperties)) {
            return Optional.empty();
        }
        return Optional.of(contextEnvironmentProperties);
    }
}
