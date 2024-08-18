package com.inyourhead.ldap.ldaputil.service.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminCredentials implements Serializable {

    @NotEmpty
    @JsonAlias({"username", "userDn", "managerDn"})
    private String username;

    @Nullable
    private String password;
}
