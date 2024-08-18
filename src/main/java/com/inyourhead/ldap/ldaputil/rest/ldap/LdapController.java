package com.inyourhead.ldap.ldaputil.rest.ldap;

import com.inyourhead.ldap.ldaputil.rest.Controller;
import com.inyourhead.ldap.ldaputil.service.exception.AuthenticationException;
import com.inyourhead.ldap.ldaputil.service.exception.ConfigurationException;
import com.inyourhead.ldap.ldaputil.service.ldap.LdapConfig;
import com.inyourhead.ldap.ldaputil.service.resolver.AuthResolver;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile({"ad", "default"})
@RequestMapping("/ldap")
class LdapController extends Controller<LdapConfig> {

    @Autowired
    public LdapController(AuthResolver authResolver) {
        super(authResolver);
    }

    @Operation(summary = "Authorize LDAP user")
    @PostMapping(path = "/authorize", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Override
    public ResponseEntity<Boolean> authorize(@RequestParam @NotEmpty @Parameter(description = "Username", example = "joe") String username,
                                             @RequestParam @NotEmpty @Parameter(description = "Password") String password,
                                             @RequestBody @NotNull @Parameter(description = "Ldap configuration") LdapConfig config) throws ConfigurationException, AuthenticationException {
        return super.authorize(username, password, config);
    }
}
