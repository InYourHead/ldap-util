package com.inyourhead.ldap.ldaputil.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class SearchConfig implements Serializable {

    /**
     * Comma separated list of urls e.q.: ldap://localhost:389,ldap://example.com
     */
    @NotEmpty
    private String urls;

    @NotEmpty
    private String baseDn;;

    @JsonIgnore
    public List<String> getUrlsList() {
        if (urls == null || urls.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.asList(urls.split(","));
    }
}
