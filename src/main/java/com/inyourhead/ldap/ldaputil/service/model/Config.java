package com.inyourhead.ldap.ldaputil.service.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
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
    private String urls;

    @JsonAlias({"baseDn", "rootDn"})
    @NotEmpty
    private String baseDn;

    @JsonIgnore
    public List<String> getUrlsList() {
        if (urls == null || urls.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.asList(urls.split(","));
    }

}
