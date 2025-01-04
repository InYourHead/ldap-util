package com.inyourhead.ldap.app.rest.ldap;

import com.inyourhead.ldap.app.rest.Controller;
import com.inyourhead.ldap.util.api.service.auth.AuthService;
import com.inyourhead.ldap.util.api.service.exception.AuthenticationException;
import com.inyourhead.ldap.util.api.service.exception.ConfigurationException;
import com.inyourhead.ldap.util.api.service.auth.ldap.LdapConfig;
import com.inyourhead.ldap.util.api.service.model.Credentials;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile({"ldap"})
@RequestMapping("/ldap")
class LdapController extends Controller<LdapConfig> {

    @Autowired
    public LdapController(AuthService<LdapConfig> authService) {
        super(authService);
    }

    @Operation(summary = "Authenticate LDAP user")
    @PostMapping(path = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Override
    public ResponseEntity<Boolean> authenticate(@Valid @NotNull @Parameter(description = "User credentials") Credentials credentials,
                                                @Valid @NotNull @RequestBody @Parameter(description = "Ldap configuration") LdapConfig config) throws ConfigurationException, AuthenticationException {
        return super.authenticate(credentials, config);
    }
}
