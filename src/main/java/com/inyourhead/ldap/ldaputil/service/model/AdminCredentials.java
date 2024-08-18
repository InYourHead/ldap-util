package com.inyourhead.ldap.ldaputil.service.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema
public class AdminCredentials implements Serializable {

    @NotEmpty
    @JsonAlias({"username", "userDn", "managerDn"})
    @Schema(description = "Admin username.")
    private String username;

    @Nullable
    @Schema(description = "Admin password.")
    private String password;
}
