package com.inyourhead.ldap.app.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomepageController {

    @GetMapping("/")
    public String index() {
        return "redirect:/swagger-ui/index.html";
    }
}
