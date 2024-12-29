package com.inyourhead.ldap.app.rest;

import com.inyourhead.ldap.app.ApplicationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc
@SpringBootTest
@ApplicationTest
public class SwaggerApplicationTest {

    @Autowired
    protected MockMvc mockMvc;

    @Test
    public void shouldDisplaySwaggerUiPage() throws Exception {

        //when
        MvcResult mvcResult = mockMvc.perform(get("/swagger-ui/index.html")).andExpect(status().isOk()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();

        //then
        Assertions.assertTrue(contentAsString.contains("Swagger UI"));
    }

    @Test
    public void shouldDisplaySwaggerUiPageAsDefaultPage() throws Exception {

        //when
        MvcResult mvcResult = mockMvc.perform(get("/")).andExpect(status().is3xxRedirection()).andReturn();

        //then
        Assertions.assertEquals("/swagger-ui/index.html", mvcResult.getResponse().getHeader("Location"));
    }


}