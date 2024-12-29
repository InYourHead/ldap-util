package com.inyourhead.ldap.app.rest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema
@Builder
public class ExceptionMessage implements Serializable {

    @Schema(description = "Error message")
    private String message;

    @Schema(description = "Error details")
    private String cause;
}
