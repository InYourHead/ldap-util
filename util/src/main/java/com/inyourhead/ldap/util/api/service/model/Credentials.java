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

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema
public class Credentials implements Serializable {

    @NotEmpty
    @JsonAlias({"username", "userDn", "managerDn"})
    @Schema(description = "Admin username.")
    private String username;

    @Nullable
    @Schema(description = "Admin password.")
    private String password;

    @JsonIgnore
    public static Credentials of(String username, String password) {
        return Credentials.builder().username(username).password(password).build();
    }
}
